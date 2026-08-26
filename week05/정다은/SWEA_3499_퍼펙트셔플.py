T = int(input())

for test_case in range(1, T + 1):
    num = int(input())
    # 홀수일 때 1덱에 1개 더 감.
    full_deck = list(input().split(" "))
    deck1 = full_deck[0 : (len(full_deck)+1)//2]
    deck2 = full_deck[(len(full_deck)+1)//2 : ]
    
    print(f"#{test_case}", end=" ")
    
    while deck1 or deck2:
        print(deck1.pop(0), end=" ")
        if deck2:
        	print(deck2.pop(0), end=" ")
    print()
