/*
 * 문제: Programmers 258712 가장 많이 받은 선물
 * 알고리즘:
 */

import java.lang.reflect.Member;
import java.util.Arrays;
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
        index = nameToIndex.get(giftsSplit[i][1]); // 받은 사람의 인덱스
        friendList[index].receive_count[nameToIndex.get(giftsSplit[i][0])]++; // 받은 사람의 receive_count 증가
        }
        return answer;
    }

    class Friend {
        private String name;
        public int[] give_count;// 각 친구들에게 준 선물
        public int[] receive_count;// 각 친구들에게 받은 선물
        private int total_count; // 선물지수

        public Friend(String name) {
            this.name = name;
        }

        public void setsize(int size) {
            give_count = new int[size];
            receive_count = new int[size];
        }

        public String getName() {
            return name;
        }

    }
}
