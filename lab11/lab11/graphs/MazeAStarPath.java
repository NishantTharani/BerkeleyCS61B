package lab11.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        // Manhattan distance
        int sx = maze.toX(v);
        int sy = maze.toY(v);
        int tx = maze.toX(t);
        int ty = maze.toY(t);

        return Math.abs(sx - tx) + Math.abs(sy - ty);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    private class mazeComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer node1, Integer node2) {
            return Integer.compare(distTo[node1] + h(node1), distTo[node2] + h(node2));
        }
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        PriorityQueue<Integer> q = new PriorityQueue<>(maze.V(), new mazeComparator());
        q.add(s);
        marked[s] = true;
        announce();

        while (!q.isEmpty()) {
            int curr = q.remove();
            marked[curr] = true;
            announce();

            if (curr == t) {
                return;
            }

            for (int next : maze.adj(curr)) {
                if (!marked[next]) {
                    distTo[next] = distTo[curr] + 1;
                    edgeTo[next] = curr;
                    q.add(next);
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

