package hw4.puzzle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board implements WorldState {

    private int[][] tiles;
    private final int n;

    public Board(int[][] tiles) {
        n = tiles.length;
        this.tiles = cloneTiles(tiles, n);
    }

    private int[][] cloneTiles(int[][] oldTiles, int oldN) {
        int[][] newTiles = new int[oldN][oldN];

        for (int i = 0; i < oldN; i++) {
            System.arraycopy(oldTiles[i], 0, newTiles[i], 0, n);
        }

        return newTiles;
    }

    public int tileAt(int i, int j) {
        if (i < 0 || j < 0 || i >= this.n || j >= this.n) {
            throw new IndexOutOfBoundsException();
        }

        return tiles[i][j];
    }

    public int size() {
        return this.n;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        List<WorldState> neighbors = new LinkedList<>();
        int blankx = -100;
        int blanky = -100;

        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (this.tiles[x][y] == 0) {
                    blankx = x;
                    blanky = y;
                }
            }
        }

        List<int[]> candidates = new ArrayList<>();
        candidates.add(new int[]{blankx - 1, blanky});
        candidates.add(new int[]{blankx + 1, blanky});
        candidates.add(new int[]{blankx, blanky - 1});
        candidates.add(new int[]{blankx, blanky + 1});

        for (int[] candidate : candidates) {
            if (candidate[0] >= 0 && candidate[1] >= 0 &&
                    candidate[0] <= n - 1 && candidate[1] <= n - 1) {
                int[][] newTiles = swapTiles(blankx, blanky, candidate[0], candidate[1]);
                Board newBoard = new Board(newTiles);
                neighbors.add(newBoard);
            }
        }


        return neighbors;
    }

    /**
     * Swap the tile at x1,y1 with the tile at x2,y2 and return a representation of the new board
     */
    private int[][] swapTiles(int x1, int y1, int x2, int y2) {
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0 || x1 >= n || y1 >= n || x2 >= n || y2 >= n) {
            throw new IndexOutOfBoundsException();
        }

        int[][] newBoard = cloneTiles(this.tiles, this.n);
        int tmp = newBoard[x2][y2];
        newBoard[x2][y2] = newBoard[x1][y1];
        newBoard[x1][y1] = tmp;

        return newBoard;
    }

    public int hamming() {
        int distance = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != 0 && this.tiles[i][j] != (i * n) + j + 1) {
                    distance++;
                }
            }
        }

        return distance;
    }

    public int manhattan() {
        int distance = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tile = this.tiles[i][j];

                if (tile == 0) {
                    continue;
                }

                int x = (tile - 1) / n;
                int y = (tile - 1) % n;

                distance += Math.abs(x - i) + Math.abs(y - j);
            }
        }

        return distance;
    }

    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    @Override
    public boolean equals(Object y) {
        Board other = (Board) y;

        if (this.n != other.n) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != other.tiles[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }

    @Override
    public int hashCode() {
        int pow = 1;
        int hash = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                hash += tiles[i][j] * pow;
                pow *= 10;
            }
        }

        return hash;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
