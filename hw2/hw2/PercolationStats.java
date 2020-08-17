package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

public class PercolationStats {
    private int numExperiments;
    private double mean;
    private double stdDev;

    /**
     * Performs T independent experiments on a percolation grid of dimensions
     * N x N, to see what what proportion of spots have to be opened before the grid percolates.
     * Stores the resulting stats in 'mean' and 'stdDev' fields.
     *
     * @param N - dimensions of percolation grid to run experiments on
     * @param T - number of experiments to be run
     * @param pf - factory that provides Percolation objects
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        numExperiments = T;
        double[] successFractions = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);
            int numOpened = 0;
            int[] orderToOpen = StdRandom.permutation(N * N);
            while (!perc.percolates()) {
                int[] coords = idxToRowCol(orderToOpen[numOpened], N);
                numOpened += 1;
                perc.open(coords[0], coords[1]);
            }
            successFractions[i] = (double) numOpened / ((double) N * N);
        }

        mean = StdStats.mean(successFractions);
        stdDev = StdStats.stddev(successFractions);
    }

    /**
     * Returns the 2D co-ordinate representation of a provided index in an array, given a
     * row length (assuming the 2D is a square).
     * @param idx - 1D representation of the 2D co-ordinates
     * @param rowLength - number of elements in each 'row' of the 2D co-ordinates
     * @return coords[0] is the row index, coords[1] is the column index
     */
    private int[] idxToRowCol(int idx, int rowLength) {
        int[] coords = new int[2];
        coords[0] = idx / rowLength;
        coords[1] = idx % rowLength;
        return coords;
    }

    /**
     * Returns the mean of the experiment results.
     * @return - mean of the experiment results
     */
    public double mean() {
        return mean;
    }

    /**
     * Returns the std dev of experiment results.
     * @return - std dev of experiment results
     */
    public double stddev() {
        return stdDev;
    }

    /**
     * Returns the lower bound of the 95% confidence interval of the experiment result's mean.
     * @return - confidence interval bound
     */
    public double confidenceLow() {
        return mean() - ((1.96 * stddev()) / Math.sqrt(numExperiments));
    }

    /**
     * Returns the upper bound of the 95% confidence interval of the experiment result's mean.
     * @return - confidence interval bound
     */
    public double confidenceHigh() {
        return mean() + ((1.96 * stddev()) / Math.sqrt(numExperiments));
    }
}
