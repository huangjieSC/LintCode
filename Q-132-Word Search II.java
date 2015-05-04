http://www.lintcode.com/en/problem/word-search-ii/#
Given a matrix of lower alphabets and a dictionary. 
Find all words in the dictionary that can be found in the matrix. 
A word can start from any position in the matrix and go left/right/up/down to the adjacent position. 

public class Solution {
    /**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    public ArrayList<String> wordSearchII(char[][] board,
        ArrayList<String> words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            root.add(word);
        }
        int nRow = board.length;
        int nCol = board[0].length;
        Set<String> resultSet = new HashSet<String>();
        boolean[][] visited = new boolean[nRow][nCol];
        for (int x = 0; x < nRow; x++) {
            for (int y = 0; y < nCol; y++) {
                search(board, visited, root, x, y, "", resultSet);
            }
        }
        return new ArrayList<String>(resultSet);
    }
 
    private void search(char[][] board, boolean[][] visited, TrieNode root,
        int x, int y, String tmp, Set<String> resultSet) {
    
        if (root == null || x < 0 || x >= board.length || y < 0 || y >= board[0].length)
            return;
        char cur = board[x][y];
        if (visited[x][y])
            return;
        if (root.child[cur] == null)
            return;
        TrieNode next = root.child[cur];
        tmp += cur;
 
        if (next.isEnd)
            resultSet.add(tmp);
     
        visited[x][y] = true;
 
        search(board, visited, next, x + 1, y, tmp, resultSet);
        search(board, visited, next, x - 1, y, tmp, resultSet);
        search(board, visited, next, x, y + 1, tmp, resultSet);
        search(board, visited, next, x, y - 1, tmp, resultSet);

        visited[x][y] = false;
    }
 
    class TrieNode {
        boolean isEnd;
        TrieNode[] child;
 
        TrieNode() {
            isEnd = false;
            child = new TrieNode[256];
        }
 
        void add(String s) {
            TrieNode t = this;
            int len = s.length();
            for (int k = 0; k < len; k++) {
                int index = s.charAt(k);
                if (t.child[index] == null)
                    t.child[index] = new TrieNode();
                t = t.child[index];
            }
            t.isEnd = true;
        }
    }
}
