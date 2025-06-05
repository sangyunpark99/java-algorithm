package 프로그래머스.예상대진표;

class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;

        while(a!=b){ // 라운드 번호가 같기 전까지
            a = (a+1) / 2;
            b = (b+1) / 2;
            answer++;
        }

        return answer;
    }
}
