from collections import deque

# 방향: 상, 하, 좌, 우
dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

# 반대 방향
# 상(0) <-> 하(1)
# 좌(2) <-> 우(3)
opposite = [1, 0, 3, 2]

# 파이프 종류별 이동 가능한 방향
pipe = {
    1: [0, 1, 2, 3],  # 상, 하, 좌, 우
    2: [0, 1],        # 상, 하
    3: [2, 3],        # 좌, 우
    4: [0, 3],        # 상, 우
    5: [1, 3],        # 하, 우
    6: [1, 2],        # 하, 좌
    7: [0, 2]         # 상, 좌
}


def bfs(R, C, L):
    queue = deque()

    # 시작 위치 + 현재 시간
    queue.append((R, C, 1))

    # 방문 여부만 저장
    visited = [[False] * M for _ in range(N)]
    visited[R][C] = True

    count = 1

    while queue:
        r, c, time = queue.popleft()

        # 현재 시간이 이미 L이면
        # 여기서 더 이동할 수 없음
        if time == L:
            continue

        # 현재 위치의 파이프 타입
        current_type = tunnel[r][c]

        # 현재 파이프가 갈 수 있는 방향만 탐색
        for d in pipe[current_type]:

            nr = r + dr[d]
            nc = c + dc[d]

            # 1. 범위 밖이면 이동 불가
            if nr < 0 or nr >= N or nc < 0 or nc >= M:
                continue

            # 2. 다음 위치에 파이프가 없으면 이동 불가
            if tunnel[nr][nc] == 0:
                continue

            # 3. 이미 방문한 곳이면 다시 방문하지 않음
            if visited[nr][nc]:
                continue

            # 다음 위치의 파이프 타입
            next_type = tunnel[nr][nc]

            # 4. 다음 파이프가 반대 방향으로 열려 있어야 함
            if opposite[d] not in pipe[next_type]:
                continue

            # 이동 가능
            visited[nr][nc] = True
            queue.append((nr, nc, time + 1))
            count += 1

    return count


T = int(input())

for test_case in range(1, T + 1):

    N, M, R, C, L = map(int, input().split())

    tunnel = [
        list(map(int, input().split()))
        for _ in range(N)
    ]

    answer = bfs(R, C, L)

    print(f"#{test_case} {answer}")
