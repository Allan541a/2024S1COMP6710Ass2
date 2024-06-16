package comp1110.ass2;

//  Authored by Yuxuan Shi U7789617.
//  this is a search algorithm in order to solve Task14 and Task15

public class PathSearch {

    //  modification parameters when moving up, down, left and right
    static int[][] movements = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


    // check if a valid path exists, Task 14
    public static boolean pathExist(BoardState board, int row_start, int col_start, int row_end, int col_end) {
        char colour = Character.toLowerCase(board.state[row_start][col_start].toChar());

        //  record visited locations
        boolean[][] visited = new boolean[board.state.length][board.state[0].length];

        return DFS(board, row_start, col_start, row_end, col_end, colour, visited);
    }


    //  depth-first search
    private static boolean DFS(BoardState board, int row_current, int col_current, int row_end, int col_end, char color, boolean[][] visited) {
        // return false if the current location is out of boundary, the colour doesn't match, or has already been visited
        if (row_current < 0 || row_current >= board.state.length ||
                col_current < 0 || col_current >= board.state[0].length ||
                (Character.toLowerCase(board.state[row_current][col_current].toChar()) != color &&
                        Character.toLowerCase(board.state[row_current][col_current].toChar()) != 'w' &&
                        Character.toLowerCase(board.state[row_current][col_current].toChar()) != 'W') ||
                visited[row_current][col_current]) {
            return false;
        }

        // return true if the current position is the destination
        if (row_current == row_end && col_current == col_end) {
            return true;
        }

        // this location is valid, but not the destination, mark this point as visited.
        visited[row_current][col_current] = true;

        // try up, down, left, or right.
        for (int[] dir : movements) {
            int nextRow = row_current + dir[0];
            int nextCol = col_current + dir[1];
            //  check the next location.
            if (DFS(board, nextRow, nextCol, row_end, col_end, color, visited)) {
                return true;
            }
        }

        //  no path is found, return false.
        return false;
    }





    //  As long as it's not completely blocked by fire, it's considered to exist. Task 15
    public static boolean potential_pathExist(BoardState board, int row_start, int col_start, int row_end, int col_end) {
        //  record visited locations
        boolean[][] visited = new boolean[board.state.length][board.state[0].length];

        return DFS2(board, row_start, col_start, row_end, col_end, visited);
    }


    private static boolean DFS2(BoardState board, int row_current, int col_current, int row_end, int col_end, boolean[][] visited) {
        // return false if the current location is out of boundary, is fire, or has already been visited
        if (row_current < 0 || row_current >= board.state.length ||
                col_current < 0 || col_current >= board.state[0].length ||
                        Character.toLowerCase(board.state[row_current][col_current].toChar()) == 'f' ||
                visited[row_current][col_current]) {
            return false;
        }

        // return true if the current position is the destination
        if (row_current == row_end && col_current == col_end) {
            return true;
        }

        // this location is valid, but not the destination, mark this point as visited.
        visited[row_current][col_current] = true;

        // try up, down, left, or right.
        for (int[] dir : movements) {
            int nextRow = row_current + dir[0];
            int nextCol = col_current + dir[1];
            //  check the next location.
            if (DFS2(board, nextRow, nextCol, row_end, col_end, visited)) {
                return true;
            }
        }

        //  no path is found, return false.
        return false;
    }


}
