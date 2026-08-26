from collections import deque

def bfs(graph, start):
    """
    시작점에서 BFS를 수행하여,
    가장 늦게 연락받은 사람 중 번호가 가장 큰 사람을 반환한다.
    """

    # distance[i]
    # 시작점으로부터 i번 사람까지 연락이 전달되는 데 걸린 단계
    # -1: 아직 연락받지 않음
    #  0: 연락 시작점
    #  1 이상: 연락받은 단계
    distance = [-1] * 101

    # BFS 탐색을 위한 큐
    queue = deque([start])

    # 시작점은 처음부터 연락을 알고 있으므로 0단계
    distance[start] = 0

    # 큐가 빌 때까지 연락을 전파한다.
    while queue:
        # 가장 먼저 연락받은 사람을 꺼낸다.
        current_person = queue.popleft()
        # 현재 사람이 연락할 수 있는 사람들을 확인한다.
        for next_person in graph[current_person]:
            # 아직 연락받지 않은 사람에게만 연락한다.
            if distance[next_person] == -1:
                # 다음 사람은 현재 사람보다 한 단계 뒤에 연락받는다.
                distance[next_person] = distance[current_person] + 1
                # 다음 사람도 이후 다른 사람에게 연락해야 하므로
                # 큐에 넣는다.
                queue.append(next_person)
    # 가장 늦은 연락 단계를 찾는다.
    max_distance = max(distance)
    # 가장 늦게 연락받은 사람 중 번호가 가장 큰 사람을 찾는다.
    answer = 0

    for person in range(1, 101):
        if distance[person] == max_distance:
            # 사람 번호를 작은 순서부터 확인하므로,
            # 같은 단계의 사람이 나올 때마다 갱신하면
            # 마지막에는 가장 큰 번호가 저장된다.
            answer = person
    return answer

T = 10

for test_case in range(1, T + 1):
    # 길이, 시작점
    length, start = map(int, input().split())
    
    # 입력 데이터들 한 줄로 받아서 리스트에 넣기
    contact = list(map(int, input().split()))
    
    graph = [[] for _ in range(101)]
    
    for i in range(0, length, 2):
        # 그래프에 관계들 다 넣기
        graph[contact[i]].append(contact[i+1])
    
    answer = bfs(graph, start)
    
    print(f"#{test_case} {answer}")
