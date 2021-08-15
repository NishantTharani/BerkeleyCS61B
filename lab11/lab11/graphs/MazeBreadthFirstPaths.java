package lab11.graphs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private Maze maze;
    private int source;
    private int target;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        source = maze.xyTo1D(sourceX, sourceY);
        target = maze.xyTo1D(targetX, targetY);
        distTo[source] = 0;
        edgeTo[source] = source;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs(int s) {
        Queue<Integer> q = new LinkedList<>();
        q.add(s);

        while (!q.isEmpty()) {
            int curr = q.remove();
            marked[curr] = true;
            announce();

            if (curr == target) {
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
        bfs(source);
    }
}

