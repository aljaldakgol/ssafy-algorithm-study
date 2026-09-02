import sys
from collections import deque

input = sys.stdin.readline

CMD_INIT = 100
CMD_ADD = 200
CMD_MOVE = 300


class UserSolution:
    # 0: 위, 1: 오른쪽, 2: 아래, 3: 왼쪽
    UP, RIGHT, DOWN, LEFT = 0, 1, 2, 3
    DX = (0, 1, 0, -1)
    DY = (-1, 0, 1, 0)

    def init(self, N):
        self.N = N

        # road[y][x] : 이 위치에서 갈 수 있는 방향 bitmask
        self.road = [[0] * N for _ in range(N)]

        # 실제 도로인지
        self.is_road = [[False] * N for _ in range(N)]

        # 해당 좌표가 교차로인지
        self.crossroad = [[False] * N for _ in range(N)]

        # edge_owner[y][x][dir]
        # 해당 물리적 도로를 사용하는 건물 ID 집합
        self.edge_owner = [
            [[set() for _ in range(4)] for _ in range(N)]
            for _ in range(N)
        ]

        # mId -> (도로 x, 도로 y, 해당 건물의 시계방향)
        self.dock = {}

        # 건물이 추가되면 교차로를 다시 계산해야 함
        self.dirty = False

    def addBuildings(self, mId, sX, sY, W, H, aX, aY):
        # 건물 바로 바깥 1칸이 도로
        left = sX - 1
        right = sX + W
        top = sY - 1
        bottom = sY + H

        # 건물 주변 시계방향 도로 생성
        # 위쪽 →
        for x in range(left, right):
            self._add_edge(x, top, self.RIGHT, mId)

        # 오른쪽 ↓
        for y in range(top, bottom):
            self._add_edge(right, y, self.DOWN, mId)

        # 아래쪽 ←
        for x in range(right, left, -1):
            self._add_edge(x, bottom, self.LEFT, mId)

        # 왼쪽 ↑
        for y in range(bottom, top, -1):
            self._add_edge(left, y, self.UP, mId)

        # 하역장은 별도 한 칸이 아니라 도로 위의 특정 지점으로 처리
        if aY == 0 and 0 < aX < W - 1:             # 위
            dock_x, dock_y, dock_dir = sX + aX, top, self.RIGHT

        elif aX == W - 1 and 0 < aY < H - 1:       # 오른쪽
            dock_x, dock_y, dock_dir = right, sY + aY, self.DOWN

        elif aY == H - 1 and 0 < aX < W - 1:       # 아래
            dock_x, dock_y, dock_dir = sX + aX, bottom, self.LEFT

        elif aX == 0 and 0 < aY < H - 1:           # 왼쪽
            dock_x, dock_y, dock_dir = left, sY + aY, self.UP

        else:
            raise ValueError(f"잘못된 하역장 위치: {mId}")

        self.dock[mId] = (dock_x, dock_y, dock_dir)
        self.dirty = True

    def move(self, mFrom, mTo, M, wayThrough):
        if self.dirty:
            self._build_crossroads()

        # wayThrough 방문 여부를 bitmask로 관리
        via_bit = {
            building_id: 1 << i
            for i, building_id in enumerate(wayThrough)
        }
        full_mask = (1 << M) - 1

        # 하역장 위치 -> 건물 ID
        dock_at = {}
        for building_id, (x, y, direction) in self.dock.items():
            dock_at.setdefault((x, y, direction), []).append(building_id)

        sx, sy, start_dir = self.dock[mFrom]
        tx, ty, target_dir = self.dock[mTo]

        start_mask = via_bit.get(mFrom, 0)

        # BFS 상태:
        # (x, y, 현재 진행방향, 방문한 경유지 bitmask, 거리)
        queue = deque([(sx, sy, start_dir, start_mask, 0)])

        state_count = self.N * self.N * 4 * (1 << M)
        visited = bytearray(state_count)

        start_state = self._encode(sx, sy, start_dir, start_mask, M)
        visited[start_state] = 1

        while queue:
            x, y, direction, mask, distance = queue.popleft()

            # 목적지 + 모든 경유지 방문
            if (
                x == tx
                and y == ty
                and direction == target_dir
                and mask == full_mask
            ):
                return distance

            for nx, ny, next_dir in self._next_positions(x, y, direction):
                next_mask = mask

                # 하역장은 별도 이동 비용 없음.
                # 해당 도로 위치에 도달하면 방문 처리만 한다.
                for building_id in dock_at.get((nx, ny, next_dir), []):
                    if building_id in via_bit:
                        next_mask |= via_bit[building_id]

                state = self._encode(nx, ny, next_dir, next_mask, M)

                if visited[state]:
                    continue

                visited[state] = 1
                queue.append((nx, ny, next_dir, next_mask, distance + 1))

        return -1

    def _add_edge(self, x, y, direction, building_id):
        nx = x + self.DX[direction]
        ny = y + self.DY[direction]

        if not self._inside(x, y) or not self._inside(nx, ny):
            return

        # 시계방향 이동 간선
        self.road[y][x] |= 1 << direction

        self.is_road[y][x] = True
        self.is_road[ny][nx] = True

        # 같은 물리적 도로임을 양쪽 끝에서 확인할 수 있도록 owner 기록
        self.edge_owner[y][x][direction].add(building_id)

        opposite = (direction + 2) % 4
        self.edge_owner[ny][nx][opposite].add(building_id)

    def _build_crossroads(self):
        for y in range(self.N):
            for x in range(self.N):
                self.crossroad[y][x] = False

                if not self.is_road[y][x]:
                    continue

                owner_patterns = set()
                degree = 0

                for direction in range(4):
                    owners = self.edge_owner[y][x][direction]

                    if not owners:
                        continue

                    degree += 1
                    owner_patterns.add(frozenset(owners))

                if degree < 2:
                    continue

                # 일반 모서리:
                # {A}, {A} → 교차로 X
                #
                # 겹친 도로 중간:
                # {A,B}, {A,B} → 교차로 X
                #
                # 겹친 도로의 시작/끝:
                # {A}, {A,B}, {B} → 교차로 O
                if len(owner_patterns) >= 2:
                    self.crossroad[y][x] = True

        self.dirty = False

    def _next_positions(self, x, y, direction):
        # 1. 교차로
        # 실제로 존재하는 시계방향 간선 중에서 방향 선택 가능
        if self.crossroad[y][x]:
            for next_dir in range(4):
                if not (self.road[y][x] & (1 << next_dir)):
                    continue

                nx = x + self.DX[next_dir]
                ny = y + self.DY[next_dir]

                if self._inside(nx, ny) and self.is_road[ny][nx]:
                    yield nx, ny, next_dir

            return

        # 2. 일반 도로 또는 겹친 도로 내부
        # 현재 방향이 존재하면 무조건 그대로 진행
        if self.road[y][x] & (1 << direction):
            nx = x + self.DX[direction]
            ny = y + self.DY[direction]

            if self._inside(nx, ny) and self.is_road[ny][nx]:
                yield nx, ny, direction
                return

        # 3. 건물 모서리
        # 직진할 수 없으면 시계방향 간선으로 꺾는다.
        opposite = (direction + 2) % 4

        for next_dir in range(4):
            if next_dir == opposite:
                continue

            if not (self.road[y][x] & (1 << next_dir)):
                continue

            nx = x + self.DX[next_dir]
            ny = y + self.DY[next_dir]

            if self._inside(nx, ny) and self.is_road[ny][nx]:
                yield nx, ny, next_dir

    def _inside(self, x, y):
        return 0 <= x < self.N and 0 <= y < self.N

    def _encode(self, x, y, direction, mask, M):
        # (x, y, direction, mask)를 하나의 정수 index로 변환
        position = y * self.N + x
        return ((position * 4 + direction) << M) | mask


def main():
    T = int(input())
    user_solution = UserSolution()

    for tc in range(1, T + 1):
        Q = int(input())
        correct = True

        for _ in range(Q):
            data = list(map(int, input().split()))
            cmd = data[0]

            if cmd == CMD_INIT:
                user_solution.init(data[1])

            elif cmd == CMD_ADD:
                _, mId, sX, sY, W, H, aX, aY = data
                user_solution.addBuildings(mId, sX, sY, W, H, aX, aY)

            elif cmd == CMD_MOVE:
                mFrom = data[1]
                mTo = data[2]
                M = data[3]

                wayThrough = data[4:4 + M]
                expected = data[4 + M]

                result = user_solution.move(mFrom, mTo, M, wayThrough)

                if result != expected:
                    correct = False
                    print(
                        f"[TC {tc}] expected={expected}, "
                        f"result={result}"
                    )

        print(f"#{tc} {'PASS' if correct else 'FAIL'}")


if __name__ == "__main__":
    main()
