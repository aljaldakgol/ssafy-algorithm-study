import java.util.HashMap;

class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        
        // 누가 선물을 많이 받을지 예측
        
        // if 두 사람이 선물을 주고받은 기록이 있다면, 이번 달까지 더 많은 선물을 준 사람이 다음 달에 하나 받음
        
        // if 주고받은 기록이 하나도 없거나, 주고받은 수가 같다면, 선물 지수가 더 큰 사람이 선물 지수가 더 작은 사람에게 선물을 하나 받음
        
        // 선물 지수 : 이번 달까지 자신이 친구들에게 준 선물의 수 - 받은 선물의 수
        
        // if 두 사람의 선물 지수도 같다면, 다음 달에 선물을 주고받지 않음
        
        // 선물을 가장 많이 받을 친구가 받을 선물의 수가 궁금
        
        // input : friends[] : 친구들의 이름을 담은 1차원 문자열 배열
        // input : gifts [] : 친구들이 주고받은 선물 기록을 담은 1차원 문자열 배열. 앞에는 준 사람. 뒤에는 받은 사람.
        
        // output : 다음달에 가장 많은 선물을 받는 친구가 받을 선물의 수
        
        // 1. 친구들의 이름과 선물을 딕셔너리로 관리
        HashMap<String, Integer> getGift = new HashMap<>();
        HashMap<String, Integer> giveGift = new HashMap<>();
        HashMap<String, Integer> giftTotal = new HashMap<>();
        for (int i=0;i<friends.length;i++) {
            getGift.put(friends[i],0);
            giveGift.put(friends[i],0);
            giftTotal.put(friends[i],0);
        }
        
        // 2. gifts를 2차원 배열로 관리
        // 3. dict에 선물 준 사람 값 추가
        // 4. dict에 선물 받은 사람 값 추가
        String[][] fGifts = new String [2][gifts.length];
        for(int i=0;i<gifts.length;i++) {
            String [] split = gifts[i].split(" ");
            fGifts[0][i] = split[0];
            giveGift.put(split[0], giveGift.get(split[0])+1);
            fGifts[1][i] = split[1];
            getGift.put(split[1], getGift.get(split[1])+1);
        }
        
        // 5. friends 이중 for문으로 각 멤버별로 gifts에서 두 사람의 이름이 함께 있는 요소 찾기
        // if 있으면 더 많이 준 사람 +1
        // if 없거나 같으면 선물 지수가 더 큰 사람이 하나 받음 (준 수 - 받은 수)
        
        int cnt1 = 0;
        int cnt2 = 0;
        for(int i=0;i<friends.length;i++) {
            for(int j=i+1;j<friends.length;j++) {
                cnt1 = 0;
                cnt2 = 0;
                // gifts 탐색
                for(int k=0;k<gifts.length;k++) {
                    if (fGifts[0][k].equals(friends[i]) && fGifts[1][k].equals(friends[j])) {
                        cnt1++;
                    }else if (fGifts[1][k].equals(friends[i]) && fGifts[0][k].equals(friends[j])) {
                        cnt2++;
                    }
                }
                
                // 선물 주고받은 수 및 선물 지수 판별
                if (cnt1 > cnt2) {
                    giftTotal.put(friends[i], giftTotal.get(friends[i])+1);
                }else if (cnt1 < cnt2){
                    giftTotal.put(friends[j], giftTotal.get(friends[j])+1);
                } else {
                    // 선물 지수 이용해서 판별
                    int f1 = giveGift.get(friends[i]) - getGift.get(friends[i]);
                    int f2 = giveGift.get(friends[j]) - getGift.get(friends[j]);
                    
                    if (f1 > f2) {
                        giftTotal.put(friends[i], giftTotal.get(friends[i])+1);
                    }else if(f2 > f1){
                        giftTotal.put(friends[j], giftTotal.get(friends[j])+1);
                    }
                }
            }
        }
        
        // 6. giftTotal dict에서 가장 큰 value의 key 값 반환
        for (int value : giftTotal.values()) {
            if (value > answer) {
                answer = value;
            }
        }
        
        return answer;
    }
}