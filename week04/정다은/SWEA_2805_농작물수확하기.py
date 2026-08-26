T = int(input())

for test_case in range(1, T + 1):
    N = int(input())
    farm = []
    value = 0

    for i in range(N):
        farm.append(list(map(int, input().strip())))

        distance = abs(i - N // 2)
        value += sum(farm[i][distance:N - distance])

    print(f"#{test_case} {value}")
