package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Models a square percolation system.
 */
public class Percolation {

    private WeightedQuickUnionUF connections;
    private WeightedQuickUnionUF connectionsTopOnly;
    private boolean[][] grid;
    private int n;
    private int numOpen;
    private int top;
    private int bottom;


    /**
     * Creates a NxN grid representing the spots in the percolation grid,
     * with all
     * spots initially set to closed (a value of false in the 2D array).
     *
     * Also creates
     * a WeightedQuickUnionUF object to represent which open spots are
     * connected to each
     * other; this can later be used to quickly see whether the grid
     * percolates by checking
     * whether any open spots in the top row are connected to any
     * spots in the bottom row.
     *
     * The item at i'th row and j'th column is represented at the
     * i*n + j + 1 index of our
     * WQUUF object. This is because index 0 of the WQUUF is used for
     * a 'virtual top site', which
     * connects to all of the spots in the top row of the grid,
     * allowing for constant time checking
     * of fullness/percolation.
     * In addition, index n * n + 1 of the larger WQUUF - 'connections'
     * - is used for a virtual bottom
     * site, which does the same thing for the bottom row.
     *
     * @param N - the number of rows & columns in the percolation grid.
     */
    public Percolation(int N) {
        n = N;
        numOpen = 0;
        top = 0;
        bottom = N * N + 1;
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        connections = new WeightedQuickUnionUF(N * N + 2);
        connectionsTopOnly = new WeightedQuickUnionUF(N * N + 1);
        grid = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                grid[i][j] = false;
            }
            connections.union(top, rowColTo1D(0, i));
            connectionsTopOnly.union(top, rowColTo1D(0, i));
            connections.union(bottom, rowColTo1D(N - 1, i));
        }
    }

    /**
     * Returns a 1D representation of the provided 2D co-ordinates,
     * calculated as
     * row * n + col + 1 (as the first index is our virtual top site).
     * @param row - row to be translated into 1D
     * @param col - col to be translated into 1D
     * @return - 1D representation of the 2D co-ordinates
     */
    private int rowColTo1D(int row, int col) {
        return (row * n) + col + 1;
    }

    /**
     * Opens up the spot located at the provided position in the
     * percolated grid. Then, if any of the
     * neighbouring (top/left/bottom/right) spots are also open,
     * connects the partitions of the two spots.
     * @param row - the row index of the spot to be opened up
     * @param col - the column index of the spot to be opened up
     */
    public void open(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw new IndexOutOfBoundsException();
        }

        if (!grid[row][col]) {
            grid[row][col] = true;
            numOpen += 1;
        }

        if (row < n - 1 && grid[row + 1][col]) {
            connections.union(rowColTo1D(row, col), rowColTo1D(row + 1, col));
            connectionsTopOnly.union(rowColTo1D(row, col), rowColTo1D(row + 1, col));
        }
        if (row > 0 && grid[row - 1][col]) {
            connections.union(rowColTo1D(row, col), rowColTo1D(row - 1, col));
            connectionsTopOnly.union(rowColTo1D(row, col), rowColTo1D(row - 1, col));
        }
        if (col < n - 1 && grid[row][col + 1]) {
            connections.union(rowColTo1D(row, col), rowColTo1D(row, col + 1));
            connectionsTopOnly.union(rowColTo1D(row, col), rowColTo1D(row, col + 1));
        }
        if (col > 0 && grid[row][col - 1]) {
            connections.union(rowColTo1D(row, col), rowColTo1D(row, col - 1));
            connectionsTopOnly.union(rowColTo1D(row, col), rowColTo1D(row, col - 1));
        }
    }

    /**
     * Returns a bool representing whether the spot at the
     * provided location in the percolation grid is open
     * (true means it is open).
     * @param row - row index of the spot to check
     * @param col - col index of the spot to check
     * @return bool - true if the given spot is open, else false
     */
    public boolean isOpen(int row, int col) {
        if (row < 0 || col < 0 || row >= n || col >= n) {
            throw new IndexOutOfBoundsException();
        }

        return grid[row][col];
    }

    /**
     * Checks whether there is a valid route from any spot on
     * the top row to the spot at the provided
     * index, by a single check to see if the provided spot is
     * connected to the 'virtual top site'.
     * @param row - row index of the spot to check full-ness of
     * @param col - col index of the spot to check full-ness of
     * @return - true if a valid route exists from the top row
     * to the provided spot, else false
     */
    public boolean isFull(int row, int col) {
        return (isOpen(row, col)
                && connectionsTopOnly.connected(top, rowColTo1D(row, col)));
    }

    /**
     * Returns the number of open spots in the grid.
     * @return int - the number of open spots in the grid.
     */
    public int numberOfOpenSites() {
        return numOpen;
    }

    /**
     * Does the system percolate? Only a single call is
     * required to check whether the virtual top
     * and virtual bottom sites are connected, since we've
     * linked them to all the spots in the top/bottom
     * rows earlier on.
     * @return true if the system percolates, else false.
     */
    public boolean percolates() {
        if (n == 1) {
            return isOpen(0, 0);
        } else {
            return connections.connected(top, bottom);
        }
    }

    /**
     * The main method. Yup. It is, apparently, needed.
     * @param args - the args. Uh-huh.
     */
    public static void main(String[] args) {
        return;
    }



}
