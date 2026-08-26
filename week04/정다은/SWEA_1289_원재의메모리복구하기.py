T = int(input())

for test_case in range(1, T + 1):
    # 리스트에 넣기
    nums = list(map(int, input().strip()))
    base = [0] * len(nums)
    
    count = 0
    while nums:
        # 맨 앞이 같다면
        if nums[0] == base[0]:
            nums.pop(0)
            base.pop(0)
        # 아닌 경우
        else:
            base = [nums[0]] * len(nums)
            count += 1
    print(f"#{test_case} {count}")
