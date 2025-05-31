package 프로그래머스.이모티콘할인행사;

import java.util.*;

class Solution {

    private int m;
    private int answerAddPerson = 0;
    private int answerPrice = 0;
    private int[] Emoticons;
    private int[][] Users;

    public int[] solution(int[][] users, int[] emoticons) {
        int[] answer = {};

        Emoticons = emoticons.clone();
        Users = new int[users.length][users[0].length];

        for(int i = 0; i < users.length; i++) {
            Users[i] = users[i].clone();
        }

        m = emoticons.length;
        permutation(new int[]{10,20,30,40}, new int[m], 0, m);
        return new int[]{answerAddPerson, answerPrice};
    }

    private void permutation(int[] arr, int[] out, int depth, int r) {
        if(depth == r) {
            check(out);
            return;
        }

        for(int i = 0; i < arr.length; i++) {
            out[depth] = arr[i];
            permutation(arr, out, depth+1, r);
        }
    }

    private void check(int[] out) {

        int totalPrice = 0;
        int totalAddPerson = 0;

        int[] discountPrice = new int[m];


        for(int i = 0; i < m; i++) { // 할인이 적용된 가격
            int curPrice = Emoticons[i]; // 현재 가격
            int price = curPrice - curPrice * out[i] / 100;// 할인된 가격
            discountPrice[i] = price; // 할인된 가격
        }

        for(int i = 0; i < Users.length; i++) { // 유저 마다
            int[] curUser = Users[i]; // 현재 유저
            int percent = curUser[0]; // 최소 percent
            int canPrice = curUser[1]; // 구매 가능 가격
            int userPerPrice = 0; // 유저 당 가격

            for(int j = 0; j < out.length; j++) {
                int curDiscount = out[j]; // 상품 할인 비율
                if(percent <= curDiscount) { // 상품 할인 비율이 더 높은 경우엔
                    userPerPrice += discountPrice[j]; // 상품의 가격을 더해준다.
                }
            }

            // 상품의 가격을 다 더한 경우
            if(canPrice <= userPerPrice) { // 할인 상품 가격의 합이 더 큰 경우
                totalAddPerson++; // 이모티콘 플러스 서비스 가입 인원 추가
            }else { // 할인 상품의 가격의 합이 더 작은 경우
                totalPrice += userPerPrice; // 할인 상품 이모티콘 구매
            }
        }

        if(answerAddPerson < totalAddPerson) { // 추가 인원이 더 많다면
            answerAddPerson = totalAddPerson; // 무조건 추가 인원 선택
            answerPrice = totalPrice; // 총 가격 갱신
        }else if(answerAddPerson == totalAddPerson && answerPrice < totalPrice) { // 추가인원이 같은 경우 가격 비교
            answerPrice = totalPrice;
        }
    }
}
