// Solved after looking at the leetcode answer :p


import java.util.Arrays;

public class BubbleGrid {
    private int[][] grid;

    /* Create new BubbleGrid with bubble/space locations specified by grid.
     * Grid is composed of only 1's and 0's, where 1's denote a bubble, and
     * 0's denote a space. */
    public BubbleGrid(int[][] grid) {
        this.grid = grid;
    }

    /* Returns an array whose i-th element is the number of bubbles that
     * fall after the i-th dart is thrown. Assume all elements of darts
     * are unique, valid locations in the grid. Must be non-destructive
     * and have no side-effects to grid. */
    public int[] popBubbles(int[][] darts) {
        // First we create a copy of the grid after all the darts have been thrown
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] endGrid = new int[rows][cols];
        for (int r = 0; r < rows; r++) {
            endGrid[r] = Arrays.copyOf(this.grid[r], this.grid[r].length);
        }

        for (int[] dart : darts) {
            endGrid[dart[0]][dart[1]] = 0;
        }

        // Then we create a WQUF data structure based on this grid
        UnionFind uf = new UnionFind(rows * cols + 1);
        // Link bubbles in the top row to a virtual ceiling bubble
        for (int i = 0; i < cols; i++) {
            if (endGrid[0][i] == 1) {
                uf.union(i, rows * cols);
            }
        }
        // Make sure all sets of orthogonal bubbles are union-ed
        for (int r = 1; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                int i = (r * cols) + c;
                // Union upwards if possible
                if (r > 0 && endGrid[r][c] == 1 && endGrid[r-1][c] == 1) {
                    uf.union(i, (r-1)*cols + c);
                }
                // Also union leftwards if possible
                if (c > 0 && endGrid[r][c] == 1 && endGrid[r][c-1] == 1) {
                    uf.union(i, r*cols + c-1);
                }
            }
        }

        /*
         Now introduce each hit in reverse order
         If the hit didn't pop a bubble, do nothing
         Otherwise, we can:
            re-create the popped bubble
            union the popped bubble with any bubbles orthogonally adjacent to it
            compare the total number of stuck bubbles before and after the pop to get the answer
         */
        int t = darts.length;
        int[] ans = new int[t];
        t--;
        int[] rc = {-1, 0, 1, 0};
        int[] cc = {0, -1, 0, 1};
        int nr;
        int nc;

        while (t >= 0) {
            // Location of the throw
            int r = darts[t][0];
            int c = darts[t][1];

            // If the hit didn't pop a bubble, do nothing
            if (this.grid[r][c] == 0) {
                ans[t] = 0;
            } else {
                // Get the number of stuck bubbles after the pop
                int postPopSize = uf.sizeOf(rows * cols);
                // Union the popped bubble with any bubbles orthogonally adjacent to it
                for (int i = 0; i < 4; i++) {
                    nr = r - rc[i];
                    nc = c - cc[i];
                    if (nr >= 0 && nc >= 0 && nr < rows && nc < rows && endGrid[nr][nc] == 1) {
                        uf.union(r * cols + c, nr * cols + nc);
                    }
                }
                // Also union the popped bubble with the virtual ceiling bubble, if it was in the top row
                if (r == 0) {
                    uf.union(rows * cols, c);
                }
                // Get the number of stuck bubbles before the pop
                int prePopSize = uf.sizeOf(rows * cols);
                // Recreate the bubble
                endGrid[r][c] = 1;
                // Store the answer
                ans[t] = Math.max(0, prePopSize - postPopSize - 1);
            }
            t--;
        }
        return ans;
    }
}
