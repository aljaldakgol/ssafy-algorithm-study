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
    // 0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽
    static final int UP = 0, RIGHT = 1, DOWN = 2, LEFT = 3;
    static final int[] DX = {0, 1, 0, -1};
    static final int[] DY = {-1, 0, 1, 0};

    int N;

    // road[y][x] : 이동 가능한 방향 bitmask
    int[][] road;

    // 실제 도로 / 교차로
    boolean[][] isRoad;
    boolean[][] crossroad;

    // edgeOwner[y][x][dir] : 해당 물리적 도로를 사용하는 건물 ID
    Set<Integer>[][][] edgeOwner;

    // mId -> {하역장 도로 x, y, 해당 건물의 시계방향}
    Map<Integer, int[]> dock;

    boolean dirty;


    @SuppressWarnings("unchecked")
    public void init(int N) {
        this.N = N;

        road = new int[N][N];
        isRoad = new boolean[N][N];
        crossroad = new boolean[N][N];
        edgeOwner = new HashSet[N][N][4];
        dock = new HashMap<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                for (int dir = 0; dir < 4; dir++) {
                    edgeOwner[y][x][dir] = new HashSet<>();
                }
            }
        }

        dirty = false;
    }


    public void addBuildings(int mId, int sX, int sY, int W, int H, int aX, int aY) {
        // 건물 바로 바깥 1칸이 도로
        int left = sX - 1;
        int right = sX + W;
        int top = sY - 1;
        int bottom = sY + H;

        // 건물 기준 시계방향 도로 생성
        for (int x = left; x < right; x++) {
            addEdge(x, top, RIGHT, mId);       // 위 →
        }

        for (int y = top; y < bottom; y++) {
            addEdge(right, y, DOWN, mId);      // 오른쪽 ↓
        }

        for (int x = right; x > left; x--) {
            addEdge(x, bottom, LEFT, mId);     // 아래 ←
        }

        for (int y = bottom; y > top; y--) {
            addEdge(left, y, UP, mId);         // 왼쪽 ↑
        }

        int dockX, dockY, dockDir;

        // 하역장은 별도 이동 칸이 아니라 도로 위의 특정 지점
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
        dirty = true;
    }


    public int move(int mFrom, int mTo, int M, int[] wayThrough) {
        if (dirty) {
            buildCrossroads();
        }

        // 경유 하역장 방문 여부 bitmask
        Map<Integer, Integer> viaBit = new HashMap<>();
        for (int i = 0; i < M; i++) {
            viaBit.put(wayThrough[i], 1 << i);
        }

        int fullMask = (1 << M) - 1;

        // (x, y, dir) -> 하역장 건물 ID
        Map<Integer, List<Integer>> dockAt = new HashMap<>();

        for (Map.Entry<Integer, int[]> entry : dock.entrySet()) {
            int buildingId = entry.getKey();
            int[] info = entry.getValue();

            int key = dockKey(info[0], info[1], info[2]);
            dockAt.computeIfAbsent(key, k -> new ArrayList<>()).add(buildingId);
        }

        int[] start = dock.get(mFrom);
        int[] target = dock.get(mTo);

        int sx = start[0];
        int sy = start[1];
        int startDir = start[2];

        int tx = target[0];
        int ty = target[1];
        int targetDir = target[2];

        int startMask = viaBit.getOrDefault(mFrom, 0);

        // BFS 상태: {x, y, direction, mask, distance}
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{sx, sy, startDir, startMask, 0});

        int stateCount = N * N * 4 * (1 << M);
        boolean[] visited = new boolean[stateCount];

        int startState = encode(sx, sy, startDir, startMask, M);
        visited[startState] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();

            int x = current[0];
            int y = current[1];
            int direction = current[2];
            int mask = current[3];
            int distance = current[4];

            // 목적지 하역장 + 모든 경유지 방문
            if (x == tx && y == ty && direction == targetDir && mask == fullMask) {
                return distance;
            }

            List<int[]> nextPositions = getNextPositions(x, y, direction);

            for (int[] next : nextPositions) {
                int nx = next[0];
                int ny = next[1];
                int nextDir = next[2];
                int nextMask = mask;

                // 하역장 방문은 추가 이동 비용 없음
                int key = dockKey(nx, ny, nextDir);

                List<Integer> buildingIds = dockAt.get(key);
                if (buildingIds != null) {
                    for (int buildingId : buildingIds) {
                        Integer bit = viaBit.get(buildingId);
                        if (bit != null) {
                            nextMask |= bit;
                        }
                    }
                }

                int state = encode(nx, ny, nextDir, nextMask, M);

                if (visited[state]) {
                    continue;
                }

                visited[state] = true;
                queue.add(new int[]{nx, ny, nextDir, nextMask, distance + 1});
            }
        }

        return -1;
    }


    private void addEdge(int x, int y, int direction, int buildingId) {
        int nx = x + DX[direction];
        int ny = y + DY[direction];

        if (!inside(x, y) || !inside(nx, ny)) {
            return;
        }

        // 시계방향 이동 가능
        road[y][x] |= 1 << direction;

        isRoad[y][x] = true;
        isRoad[ny][nx] = true;

        // 물리적 도로가 어느 건물에 의해 만들어졌는지 저장
        edgeOwner[y][x][direction].add(buildingId);

        int opposite = (direction + 2) % 4;
        edgeOwner[ny][nx][opposite].add(buildingId);
    }


    private void buildCrossroads() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                crossroad[y][x] = false;

                if (!isRoad[y][x]) {
                    continue;
                }

                Set<Set<Integer>> ownerPatterns = new HashSet<>();
                int degree = 0;

                for (int direction = 0; direction < 4; direction++) {
                    Set<Integer> owners = edgeOwner[y][x][direction];

                    if (owners.isEmpty()) {
                        continue;
                    }

                    degree++;
                    ownerPatterns.add(new HashSet<>(owners));
                }

                if (degree < 2) {
                    continue;
                }

                /*
                 * 건물 모서리
                 * {A}, {A}
                 * → 교차로 X
                 *
                 * 겹친 도로 내부
                 * {A,B}, {A,B}
                 * → 교차로 X
                 *
                 * 도로가 합쳐지거나 갈라지는 곳
                 * {A}, {A,B}, {B}
                 * → 교차로 O
                 */
                if (ownerPatterns.size() >= 2) {
                    crossroad[y][x] = true;
                }
            }
        }

        dirty = false;
    }


    private List<int[]> getNextPositions(int x, int y, int direction) {
        List<int[]> result = new ArrayList<>(4);

        // 1. 교차로
        // 실제로 존재하는 시계방향 간선들 중 선택
        if (crossroad[y][x]) {
            for (int nextDir = 0; nextDir < 4; nextDir++) {
                if ((road[y][x] & (1 << nextDir)) == 0) {
                    continue;
                }

                int nx = x + DX[nextDir];
                int ny = y + DY[nextDir];

                if (inside(nx, ny) && isRoad[ny][nx]) {
                    result.add(new int[]{nx, ny, nextDir});
                }
            }

            return result;
        }

        // 2. 일반 도로 / 겹친 도로 내부
        // 현재 방향으로 갈 수 있으면 계속 직진
        if ((road[y][x] & (1 << direction)) != 0) {
            int nx = x + DX[direction];
            int ny = y + DY[direction];

            if (inside(nx, ny) && isRoad[ny][nx]) {
                result.add(new int[]{nx, ny, direction});
                return result;
            }
        }

        // 3. 건물 모서리
        // 직진이 안 되면 등록된 시계방향 간선으로 회전
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

            if (inside(nx, ny) && isRoad[ny][nx]) {
                result.add(new int[]{nx, ny, nextDir});
            }
        }

        return result;
    }


    private boolean inside(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }


    private int encode(int x, int y, int direction, int mask, int M) {
        int position = y * N + x;
        return ((position * 4 + direction) << M) | mask;
    }


    private int dockKey(int x, int y, int direction) {
        return ((y * N + x) << 2) | direction;
    }
}
