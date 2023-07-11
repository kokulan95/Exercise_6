public class Environment {
    private int[][] grid;
    private static final int EXIT_X = 4;
    private static final int EXIT_Y = 8;

    public Environment() {
        this.grid = new int[5][10];

        // set default difficulty to 1
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                grid[i][j] = 1;
            }
        }

        // set slippery fields
        int[][] slippery = {{3,0}, {3,3}, {4,3}, {2,5}, {1,6}, {0,7}};
        for (int[] pos : slippery) {
            grid[pos[0]][pos[1]] = -3;
        }

        // set trap fields
        int[][] traps = {{0,1}, {2,2}, {3,8}, {2,9}};
        for (int[] pos : traps) {
            grid[pos[0]][pos[1]] = -10;
        }

        // set impassable fields
        int[][] impassable = {{1,3}, {2,3}, {4,6}, {3,7}, {4,7}};
        for (int[] pos : impassable) {
            grid[pos[0]][pos[1]] = Integer.MAX_VALUE;
        }

        // set exit
        grid[EXIT_X][EXIT_Y] = 10;
    }

    public int getDifficulty(int x, int y) {
        return grid[x][y];
    }

    public boolean isExit(int x, int y) {
        return x == EXIT_X && y == EXIT_Y;
    }
}
