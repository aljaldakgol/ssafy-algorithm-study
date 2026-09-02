import java.io.*;
import java.util.*;

public class Main {
    static final int CMD_INIT = 100;
    static final int CMD_ADD = 200;
    static final int CMD_MOVE = 300;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        UserSolution userSolution = new UserSolution();

        for (int tc = 1; tc <= T; tc++) {
            int Q = Integer.parseInt(br.readLine());
            boolean correct = true;

            for (int q = 0; q < Q; q++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());

                if (cmd == CMD_INIT) {
                    int N = Integer.parseInt(st.nextToken());
                    userSolution.init(N);

                } else if (cmd == CMD_ADD) {
                    int mId = Integer.parseInt(st.nextToken());
                    int sX = Integer.parseInt(st.nextToken());
                    int sY = Integer.parseInt(st.nextToken());
                    int W = Integer.parseInt(st.nextToken());
                    int H = Integer.parseInt(st.nextToken());
                    int aX = Integer.parseInt(st.nextToken());
                    int aY = Integer.parseInt(st.nextToken());

                    userSolution.addBuildings(mId, sX, sY, W, H, aX, aY);

                } else if (cmd == CMD_MOVE) {
                    int mFrom = Integer.parseInt(st.nextToken());
                    int mTo = Integer.parseInt(st.nextToken());
                    int M = Integer.parseInt(st.nextToken());

                    int[] wayThrough = new int[M];
                    for (int i = 0; i < M; i++) {
                        wayThrough[i] = Integer.parseInt(st.nextToken());
                    }

                    int expected = Integer.parseInt(st.nextToken());
                    int result = userSolution.move(mFrom, mTo, M, wayThrough);

                    if (result != expected) {
                        correct = false;
                        System.out.println(
                                "[TC " + tc + "] expected=" + expected + ", result=" + result
                        );
                    }
                }
            }

            System.out.println("#" + tc + " " + (correct ? "PASS" : "FAIL"));
        }
    }
}


class UserSolution {
    static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};
    static final int INF = 1_000_000_000;

    int N;

    // road[y][x] : 현재 위치에서 이동 가능한 방향 bitmask
    int[][] road;
    boolean[][] isRoad;
    boolean[][] crossroad;

    // edgeOwner[y][x][dir] : 해당 물리적 도로를 사용하는 건물
    Set<Integer>[][][] edgeOwner;

    // mId -> {dockX, dockY, clockwiseDirection}
    Map<Integer, int[]> dock;

    // 건물 ID를 0부터 시작하는 index로 변환
    Map<Integer, Integer> buildingIndex;
    List<Integer> buildingIds;

    // source building -> 모든 하역장까지의 최단거리
    Map<Integer, int[]> distanceCache;

    // BFS 재사용 배열
    int[] queue;
    int[] visited;
    int[] stateDistance;
    int visitId;

    boolean dirty;


    @SuppressWarnings("unchecked")
    public void init(int N) {
        this.N = N;

        road = new int[N][N];
        isRoad = new boolean[N][N];
        crossroad = new boolean[N][N];
        edgeOwner = new HashSet[N][N][4];

        dock = new HashMap<>();
        buildingIndex = new HashMap<>();
        buildingIds = new ArrayList<>();
        distanceCache = new HashMap<>();

        int stateCount = N * N * 4;
        queue = new int[stateCount];
        visited = new int[stateCount];
        stateDistance = new int[stateCount];

        visitId = 0;
        dirty = false;
    }


    public void addBuildings(int mId, int sX, int sY, int W, int H, int aX, int aY) {
        int left = sX - 1;
        int right = sX + W;
        int top = sY - 1;
        int bottom = sY + H;

        // 건물 기준 시계방향 도로
        for (int x = left; x < right; x++) {
            addEdge(x, top, RIGHT, mId);
        }

        for (int y = top; y < bottom; y++) {
            addEdge(right, y, DOWN, mId);
        }

        for (int x = right; x > left; x--) {
            addEdge(x, bottom, LEFT, mId);
        }

        for (int y = bottom; y > top; y--) {
            addEdge(left, y, UP, mId);
        }

        int dockX, dockY, dockDir;

        // 하역장은 별도의 이동 칸이 아니다.
        if (aY == 0 && 0 < aX && aX < W - 1) {
            dockX = sX + aX;
            dockY = top;
            dockDir = RIGHT;

        } else if (aX == W - 1 && 0 < aY && aY < H - 1) {
            dockX = right;
            dockY = sY + aY;
            dockDir = DOWN;

        } else if (aY == H - 1 && 0 < aX && aX < W - 1) {
            dockX = sX + aX;
            dockY = bottom;
            dockDir = LEFT;

        } else if (aX == 0 && 0 < aY && aY < H - 1) {
            dockX = left;
            dockY = sY + aY;
            dockDir = UP;

        } else {
            throw new IllegalArgumentException("잘못된 하역장 위치: " + mId);
        }

        dock.put(mId, new int[]{dockX, dockY, dockDir});

        if (!buildingIndex.containsKey(mId)) {
            buildingIndex.put(mId, buildingIds.size());
            buildingIds.add(mId);
        }

        // 건물이 추가되면 도로 구조가 달라지므로 기존 BFS 캐시 무효화
        distanceCache.clear();
        dirty = true;
    }


    /*
     * 이동 문제를 두 부분으로 분리
     *
     * 1. 하역장 간 최단거리 : BFS
     * 2. 경유지 방문 순서   : Bitmask DP
     */
    public int move(int mFrom, int mTo, int M, int[] wayThrough) {
        if (dirty) {
            buildCrossroads();
        }

        // 경유지가 없으면 출발 -> 도착 최단거리만 필요
        if (M == 0) {
            int distance = getDistance(mFrom, mTo);
            return distance >= INF ? -1 : distance;
        }

        int fullMask = (1 << M) - 1;
        int[][] dp = new int[1 << M][M];

        for (int[] row : dp) {
            Arrays.fill(row, INF);
        }

        // 출발지 -> 첫 번째 경유지
        for (int i = 0; i < M; i++) {
            int distance = getDistance(mFrom, wayThrough[i]);

            if (distance < INF) {
                dp[1 << i][i] = distance;
            }
        }

        // dp[mask][last]
        // mask의 하역장을 방문했고 현재 last에 있을 때 최소 거리
        for (int mask = 1; mask <= fullMask; mask++) {
            for (int last = 0; last < M; last++) {
                if ((mask & (1 << last)) == 0 || dp[mask][last] == INF) {
                    continue;
                }

                for (int next = 0; next < M; next++) {
                    if ((mask & (1 << next)) != 0) {
                        continue;
                    }

                    int distance = getDistance(
                            wayThrough[last],
                            wayThrough[next]
                    );

                    if (distance >= INF) {
                        continue;
                    }

                    int nextMask = mask | (1 << next);
                    int newDistance = dp[mask][last] + distance;

                    if (newDistance < dp[nextMask][next]) {
                        dp[nextMask][next] = newDistance;
                    }
                }
            }
        }

        // 마지막 경유지 -> 목적지
        int answer = INF;

        for (int last = 0; last < M; last++) {
            if (dp[fullMask][last] == INF) {
                continue;
            }

            int distance = getDistance(wayThrough[last], mTo);

            if (distance < INF) {
                answer = Math.min(
                        answer,
                        dp[fullMask][last] + distance
                );
            }
        }

        return answer >= INF ? -1 : answer;
    }


    private void addEdge(int x, int y, int direction, int buildingId) {
        int nx = x + DX[direction];
        int ny = y + DY[direction];

        if (!inside(x, y) || !inside(nx, ny)) {
            return;
        }

        road[y][x] |= 1 << direction;
        isRoad[y][x] = true;
        isRoad[ny][nx] = true;

        addOwner(x, y, direction, buildingId);

        int opposite = (direction + 2) % 4;
        addOwner(nx, ny, opposite, buildingId);
    }


    private void addOwner(int x, int y, int direction, int buildingId) {
        if (edgeOwner[y][x][direction] == null) {
            edgeOwner[y][x][direction] = new HashSet<>();
        }

        edgeOwner[y][x][direction].add(buildingId);
    }


    /*
     * incident edge의 owner 구성이 달라지는 지점을 교차로로 판단
     *
     * {A}, {A}       -> 일반 모서리
     * {A,B}, {A,B}   -> 겹친 도로 내부
     * {A}, {A,B} ... -> 합류 / 분기 지점
     */
    private void buildCrossroads() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                crossroad[y][x] = false;

                if (!isRoad[y][x]) {
                    continue;
                }

                Set<Integer> firstOwners = null;
                int degree = 0;
                boolean different = false;

                for (int direction = 0; direction < 4; direction++) {
                    Set<Integer> owners = edgeOwner[y][x][direction];

                    if (owners == null) {
                        continue;
                    }

                    degree++;

                    if (firstOwners == null) {
                        firstOwners = owners;
                    } else if (!firstOwners.equals(owners)) {
                        different = true;
                    }
                }

                if (degree >= 2 && different) {
                    crossroad[y][x] = true;
                }
            }
        }

        dirty = false;
    }


    /*
     * 이미 해당 하역장에서 BFS를 수행했다면 캐시 사용
     */
    private int getDistance(int fromId, int toId) {
        int[] distances = distanceCache.get(fromId);

        if (distances == null) {
            distances = bfs(fromId);
            distanceCache.put(fromId, distances);
        }

        Integer targetIndex = buildingIndex.get(toId);

        if (targetIndex == null) {
            return INF;
        }

        return distances[targetIndex];
    }


    /*
     * BFS 상태는 (x, y, direction)만 사용
     *
     * 경유지 방문 여부는 BFS에서 제거하고
     * move()의 Bitmask DP에서 담당한다.
     */
    private int[] bfs(int startBuildingId) {
        int[] start = dock.get(startBuildingId);

        int sx = start[0];
        int sy = start[1];
        int startDir = start[2];

        if (visitId == Integer.MAX_VALUE) {
            Arrays.fill(visited, 0);
            visitId = 0;
        }

        visitId++;

        int head = 0;
        int tail = 0;

        int startState = encodeState(sx, sy, startDir);

        queue[tail++] = startState;
        visited[startState] = visitId;
        stateDistance[startState] = 0;

        while (head < tail) {
            int state = queue[head++];

            int direction = state & 3;
            int position = state >> 2;

            int x = position % N;
            int y = position / N;

            int currentDistance = stateDistance[state];

            // 교차로 : 유효한 시계방향 간선으로 분기
            if (crossroad[y][x]) {
                for (int nextDir = 0; nextDir < 4; nextDir++) {
                    if ((road[y][x] & (1 << nextDir)) == 0) {
                        continue;
                    }

                    int nx = x + DX[nextDir];
                    int ny = y + DY[nextDir];

                    tail = pushState(
                            nx,
                            ny,
                            nextDir,
                            currentDistance + 1,
                            tail
                    );
                }

                continue;
            }

            // 일반 도로 / 겹친 도로 : 현재 방향 유지
            if ((road[y][x] & (1 << direction)) != 0) {
                int nx = x + DX[direction];
                int ny = y + DY[direction];

                tail = pushState(
                        nx,
                        ny,
                        direction,
                        currentDistance + 1,
                        tail
                );

                continue;
            }

            // 건물 모서리 : 직진이 안 되면 시계방향 간선으로 회전
            int opposite = (direction + 2) % 4;

            for (int nextDir = 0; nextDir < 4; nextDir++) {
                if (nextDir == opposite) {
                    continue;
                }

                if ((road[y][x] & (1 << nextDir)) == 0) {
                    continue;
                }

                int nx = x + DX[nextDir];
                int ny = y + DY[nextDir];

                tail = pushState(
                        nx,
                        ny,
                        nextDir,
                        currentDistance + 1,
                        tail
                );
            }
        }

        // BFS 한 번으로 현재 등록된 모든 하역장까지의 거리 저장
        int[] result = new int[buildingIds.size()];
        Arrays.fill(result, INF);

        for (int i = 0; i < buildingIds.size(); i++) {
            int buildingId = buildingIds.get(i);
            int[] target = dock.get(buildingId);

            int targetState = encodeState(
                    target[0],
                    target[1],
                    target[2]
            );

            if (visited[targetState] == visitId) {
                result[i] = stateDistance[targetState];
            }
        }

        return result;
    }


    private int pushState(
            int x,
            int y,
            int direction,
            int distance,
            int tail
    ) {
        int nextState = encodeState(x, y, direction);

        if (visited[nextState] == visitId) {
            return tail;
        }

        visited[nextState] = visitId;
        stateDistance[nextState] = distance;
        queue[tail++] = nextState;

        return tail;
    }


    private int encodeState(int x, int y, int direction) {
        return ((y * N + x) << 2) | direction;
    }


    private boolean inside(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}
