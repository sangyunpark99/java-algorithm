import java.util.*;

class Solution {
    
    private int holeCnt; // 구멍 갯수
    private int[][] board;
    
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = true;
        
        board = new int[lock.length + key.length * 2][lock.length + key.length * 2];
        
        for(int i = 0; i < board.length; i++) {
            Arrays.fill(board[i], 100);
        }
        
        for(int i = 0; i < lock.length; i++) {
            for(int j = 0; j < lock.length; j++) {
                board[i + key.length][j + key.length] = lock[i][j];
                if(lock[i][j] == 0) holeCnt++;
            }
        }
        
        
        if(holeCnt == 0) return true;
    
        // 0도
        int[][] zeroKey = key;
        boolean a = check(zeroKey, lock);
        if(a) return true;
        
        // 90도
        int[][] nineKey = rotate(zeroKey);
        boolean b = check(nineKey, lock);
        if(b) return true;
        
        // 180도
        int[][] oneEightKey = rotate(nineKey);
        boolean c = check(oneEightKey, lock);
        if(c) return true;
        
        // 270도
        int[][] twoSevenKey = rotate(oneEightKey);
        boolean d =check(twoSevenKey, lock);
        if(d) return true;
        
        
        return false;
    }
    
    private int[][] rotate(int[][] key) { // 오른쪽으로 90도 회전
        int n = key.length;
        int[][] rotatedKey = new int[n][n];
    
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                rotatedKey[i][j] = key[n-1-j][i];
            }
        }
        
        return rotatedKey;
    }
    
    private boolean check(int[][] key, int[][] lock) {
        int n = key.length;
        int H = board.length;
        
        for(int i = 0; i <= H - n; i++) {
            for(int j = 0; j <= H - n; j++) {
                int cnt = 0;
                boolean ok = true;
                
                for(int y = 0; y < key.length; y++) {
                    for(int x = 0; x < key.length; x++) {
                        if(key[y][x] == 0) continue; // 홈이 파인 부분
                        int value = board[y + i][x + j];
                        if(value == 1) {
                            ok = false;
                            break;
                        }
                        
                        if(value == 0) {
                            cnt++;
                        }
                    }
                    if(!ok) break;
                }
                
                if(ok && cnt == holeCnt) return true;
            }
        }
        
        return false;
    }
}