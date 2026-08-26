T = int(input())

for test_case in range(1, T + 1):
    space = []
    count = 0

    N, k = map(int, input().split())

    # map 채우면서 가로 탐색
    for i in range(N):
        space.append(list(map(int, input().split())))
		
        # 흰 공간의 총 수가 k보다 작으면 탐색하지 않고 skip
        if sum(space[i]) >= k:
            status = 0

            for j in range(N):
                if space[i][j] == 1:
                    status += 1
                elif space[i][j] == 0:
                    if status == k:
                        count += 1
                    status = 0
            if status == k:
                count += 1    

    # 세로 탐색
    for i in range(N):
        if sum(space[j][i] for j in range(N)) >= k:
            status = 0
			
            # 별로 숫자가 안 커서 중단조건 안넣음. 만약 컸다면 n+1 구간 있을 때 종료
            for j in range(N):
                if space[j][i] == 1:
                    status += 1
                elif space[j][i] == 0:
                    if status == k:
                        count += 1
                    status = 0
            if status == k:
                count += 1

    print("#" + str(test_case) + " " + str(count))
