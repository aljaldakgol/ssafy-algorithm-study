/*
 * 문제: Programmers 258712 가장 많이 받은 선물
 * 알고리즘: 완전탐색 (모든 친구 쌍 비교)
 *
 * [풀이 도움 여부]
 * 선물 지수 계산, 친구 쌍 비교/판정 로직, 코드 효율화(중복 배열 제거)에 AI(Claude Code)의 도움을 받아 해결
 *
 * [나의 생각 과정]
 * 1. 친구 쌍마다 주고받은 선물 수를 비교해서 다음 달 수령자를 정하고, 같으면 선물 지수로 비교해야겠다고 생각했다.
 * 2. Friend 클래스를 만들어 각 친구가 다른 친구들에게 준/받은 선물 수를 배열(give_count, receive_count)로 관리하도록 설계하고,
 *    선물 기록 파싱과 이름-인덱스 매핑까지 직접 작성했다.
 *
 * [막힌 부분]
 * 선물 지수를 실제로 계산하는 부분, 모든 친구 쌍을 판정 규칙(더 많이 준 쪽 -> 선물 지수 큰 쪽 -> 무승부)에 따라
 * 비교하고 결과를 누적해서 최댓값을 구하는 구체적인 구현에서 막혔다.
 *
 * [최종적으로 이해한 해결 방법]
 * 모든 친구 쌍(i, j)을 한 번씩만 비교해, 서로 준 횟수(give_count)가 다르면 더 많이 준 쪽이,
 * 같으면 선물 지수(준 선물 총합 - 받은 선물 총합)가 큰 쪽이 다음 달 선물을 받는다는 규칙을 적용했다.
 * 또한 "j가 i에게 받은 횟수"는 "i가 j에게 준 횟수"와 같은 정보라, receive_count 없이
 * give_count만으로 모든 계산이 가능하다는 것을 이해했다.
 */

import java.util.HashMap;
import java.util.Map;

public class Solution_가장많이받은선물_곽정민 {

    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        Friend[] friendList = new Friend[friends.length];
        String[][] giftsSplit = new String[gifts.length][2];
        for (int i = 0; i < friendList.length; i++) {
            friendList[i] = new Friend(friends[i]);
            friendList[i].setsize(friends.length);
        }
        for (int i = 0; i < gifts.length; i++) {
            String[] parts = gifts[i].split(" "); // "muzi frodo" -> ["muzi", "frodo"]
            giftsSplit[i][0] = parts[0]; // 준 사람
            giftsSplit[i][1] = parts[1]; // 받은 사람
        }

        Map<String, Integer> nameToIndex = new HashMap<>();
        for (int i = 0; i < friendList.length; i++) {
            nameToIndex.put(friendList[i].name, i);
        }
        for(int i = 0; i<giftsSplit.length; ++i){
        int index = nameToIndex.get(giftsSplit[i][0]); // 준 사람의 인덱스
        friendList[index].give_count[nameToIndex.get(giftsSplit[i][1])]++; // 준 사람의 give_count 증가
        // receive_count는 give_count와 완전히 중복된 정보라 별도로 저장하지 않는다.
        // (j가 i에게 받은 횟수 == i가 j에게 준 횟수, 즉 friendList[i].give_count[j])
        }

        // STEP 1: 각 친구의 선물 지수(= 준 선물 총합 - 받은 선물 총합) 계산
        for (int i = 0; i < friendList.length; i++) {
            friendList[i].calculateGiftIndex(friendList, i);
        }

        // STEP 2~3: 모든 친구 쌍(i, j)을 한 번씩만 비교하며(j는 i+1부터) 다음 달 수령자를 판정하고 누적
        int[] nextMonthReceived = new int[friendList.length]; // STEP 3: 다음 달 각자 받을 선물 수

        for (int i = 0; i < friendList.length; i++) {
            for (int j = i + 1; j < friendList.length; j++) {
                int giveIJ = friendList[i].give_count[j]; // i가 j에게 준 횟수
                int giveJI = friendList[j].give_count[i]; // j가 i에게 준 횟수

                int winner = -1; // -1 = 다음 달 아무도 안 받음

                if (giveIJ > giveJI) {
                    winner = i; // 더 많이 준 쪽(i)이 받는다
                } else if (giveIJ < giveJI) {
                    winner = j; // 더 많이 준 쪽(j)이 받는다
                } else if (friendList[i].total_count > friendList[j].total_count) {
                    winner = i; // 주고받은 수가 같으면 선물 지수가 큰 쪽이 받는다
                } else if (friendList[i].total_count < friendList[j].total_count) {
                    winner = j;
                }
                // 선물 지수까지 같으면 winner는 -1 그대로 -> 아무도 안 받음

                if (winner != -1) {
                    nextMonthReceived[winner]++;
                }
            }
        }

        // STEP 4: 다음 달 가장 많이 받는 친구의 선물 수가 곧 answer
        for (int i = 0; i < nextMonthReceived.length; i++) {
            answer = Math.max(answer, nextMonthReceived[i]);
        }

        return answer;
    }

    class Friend {
        private String name;
        public int[] give_count;// 각 친구들에게 준 선물 (인덱스 j = j번째 친구에게 준 횟수)
        private int total_count; // 선물지수

        public Friend(String name) {
            this.name = name;
        }

        public void setsize(int size) {
            give_count = new int[size];
        }

        public String getName() {
            return name;
        }

        // STEP 1: 선물 지수 = 내가 준 선물 총합 - 내가 받은 선물 총합
        // receive_count를 따로 안 두므로, "내가 받은 선물"은 다른 모든 친구의 give_count[myIndex]를 모아서 구한다.
        public void calculateGiftIndex(Friend[] friendList, int myIndex) {
            int totalGive = 0;
            for (int i = 0; i < give_count.length; i++) {
                totalGive += give_count[i];
            }
            int totalReceive = 0;
            for (int j = 0; j < friendList.length; j++) {
                totalReceive += friendList[j].give_count[myIndex]; // j가 나(myIndex)에게 준 횟수
            }
            total_count = totalGive - totalReceive;
        }

    }
}
