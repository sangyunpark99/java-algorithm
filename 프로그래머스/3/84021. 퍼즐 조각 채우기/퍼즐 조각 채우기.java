import java.util.*;

class Solution {
    
    private int[] dy = new int[]{-1,0,1,0};
    private int[] dx = new int[]{0,1,0,-1};
    
    private List<List<int[]>> blanks = new ArrayList<>();
    private int[][] GameBoard;
    private boolean[][] gameBoardVisited;
    
    private int[][] Table;
    private List<List<int[]>> puzzles = new ArrayList<>();
    private boolean[][] tableVisited;
    
    private int answer;
    
    public int solution(int[][] game_board, int[][] table) {
        GameBoard = game_board;
        Table = table;
        
        findBlank();
        findPuzzle();
        checkBlankAndPuzzle();
        
        return answer;
    }
    
    private void checkBlankAndPuzzle() {
        answer = 0;
        boolean[] usedPuzzle = new boolean[puzzles.size()];
        
        for (List<int[]> blank : blanks) {
            for (int i = 0; i < puzzles.size(); i++) {
                if (usedPuzzle[i]) continue;
                
                if (check(blank, puzzles.get(i))) {
                    answer += blank.size();
                    usedPuzzle[i] = true;
                    break;
                }
            }
        }
    }
    
    private boolean check(List<int[]> blank, List<int[]> puzzle) {
        List<int[]> normBlank = fixPosition(blank);
        List<int[]> normPuzzle = fixPosition(puzzle);
        
        for (int i = 0; i < 4; i++) {
            if (isSameShape(normBlank, normPuzzle)) return true;
            normPuzzle = rotatePuzzle(normPuzzle);
        }
        
        return false;
    }
    
    private List<int[]> rotatePuzzle(List<int[]> puzzle) {
        List<int[]> rotatedPuzzle = new ArrayList<>();
        
        for (int[] point : puzzle) {
            rotatedPuzzle.add(new int[]{point[1], -point[0]});
        }
        
        return fixPosition(rotatedPuzzle);
    }
    
    private List<int[]> fixPosition(List<int[]> positions) {
        int minY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;
        
        for (int[] position : positions) {
            minY = Math.min(minY, position[0]);
            minX = Math.min(minX, position[1]);
        }
        
        List<int[]> normalized = new ArrayList<>();
        for (int[] position : positions) {
            normalized.add(new int[]{position[0] - minY, position[1] - minX});
        }
        
        // 정렬 추가하여 리스트 순서 상관없도록 보장
        normalized.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        
        return normalized;
    }
    
    private boolean isSameShape(List<int[]> shape1, List<int[]> shape2) {
        if (shape1.size() != shape2.size()) return false;
        
        for (int i = 0; i < shape1.size(); i++) {
            if (!Arrays.equals(shape1.get(i), shape2.get(i))) return false;
        }
        
        return true;
    }

    private void findPuzzle() {
        tableVisited = new boolean[Table.length][Table[0].length];
        
        for (int i = 0; i < Table.length; i++) {
            for (int j = 0; j < Table[0].length; j++) {
                if (!tableVisited[i][j] && Table[i][j] == 1) {
                    List<int[]> puzzle = new ArrayList<>();
                    dfs(i, j, puzzle, Table, tableVisited, 1);
                    puzzles.add(fixPosition(puzzle));
                }
            }
        }
    }
    
    private void findBlank() {
        gameBoardVisited = new boolean[GameBoard.length][GameBoard[0].length];
        
        for (int i = 0; i < GameBoard.length; i++) {
            for (int j = 0; j < GameBoard[0].length; j++) {
                if (!gameBoardVisited[i][j] && GameBoard[i][j] == 0) {
                    List<int[]> blank = new ArrayList<>();
                    dfs(i, j, blank, GameBoard, gameBoardVisited, 0);
                    blanks.add(fixPosition(blank));
                }
            }
        }
    }
    
    private void dfs(int y, int x, List<int[]> shape, int[][] board, boolean[][] visited, int target) {
        int n = board.length;
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{y, x});
        visited[y][x] = true;

        while (!stack.isEmpty()) {
            int[] cur = stack.pop();
            shape.add(cur);

            for (int d = 0; d < 4; d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];

                if (ny >= 0 && ny < n && nx >= 0 && nx < n && !visited[ny][nx] && board[ny][nx] == target) {
                    visited[ny][nx] = true;
                    stack.push(new int[]{ny, nx});
                }
            }
        }
    }
}