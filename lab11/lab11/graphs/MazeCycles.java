package lab11.graphs;

import edu.princeton.cs.algs4.Stack;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int source;
    private Maze maze;
    private int[] parents;

    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        parents = new int[maze.V()];
        source = 0;
        parents[source] = -1;
    }

    @Override
    public void solve() {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(0);

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            marked[curr] = true;
            announce();

            for (int next : maze.adj(curr)) {
                if (!marked[next]) {
                    stack.push(next);
                    parents[next] = curr;
                } else if (next != parents[curr]) {
                    int parent = findCommonParent(curr, next);
                    drawCycle(parent, curr, next);
                    return;
                }
            }
        }
    }

    private int findCommonParent(int finder, int found) {
        // Mark all the ancestors of found
        boolean[] foundParents = new boolean[maze.V()];

        while (found != -1) {
            foundParents[found] = true;
            found = parents[found];
        }

        // Search the parents of finder until we come across an ancestor of found
        while (!foundParents[finder]) {
            finder = parents[finder];
        }

        return finder;
    }

    public void drawCycle(int parent, int finder, int found) {
        edgeTo[finder] = found;
        announce();

        int prev;

        while (finder != parent) {
            prev = finder;
            finder = parents[finder];
            edgeTo[finder] = prev;
            announce();
        }

        while (found != parent) {
            prev = found;
            found = parents[found];
            edgeTo[prev] = found;
            announce();
        }

    }

}

