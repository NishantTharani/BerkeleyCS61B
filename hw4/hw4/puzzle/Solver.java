package hw4.puzzle;
import edu.princeton.cs.algs4.MinPQ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solver {
    MinPQ<SearchNode> pq;
    SearchNode goalNode;

    private class SearchNode implements Comparable<SearchNode> {
        final WorldState state;
        final int numMoves;
        final SearchNode prev;

        public SearchNode(WorldState state, int numMoves, SearchNode prev) {
            this.state = state;
            this.numMoves = numMoves;
            this.prev = prev;
        }

        @Override
        public int compareTo(SearchNode o) {
            int me = numMoves + state.estimatedDistanceToGoal();
            int other = o.numMoves + o.state.estimatedDistanceToGoal();
            if (me > other) {
                return 1;
            } else if (me == other) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    /**
     * Solves the puzzle, computing everything necessary
     * @param initial
     */
    public Solver(WorldState initial) {
        SearchNode initialNode = new SearchNode(initial, 0, null);
        pq = new MinPQ<>();
        pq.insert(initialNode);
        SearchNode curr;

        while (goalNode == null) {
            curr = pq.delMin();
            if (curr.state.isGoal()) {
                goalNode = curr;
            } else {
                Iterable<WorldState> neighbors = curr.state.neighbors();
                for (WorldState adjState : neighbors) {
                    if (curr.prev == null || !adjState.equals(curr.prev.state)) {
                        pq.insert(new SearchNode(adjState, curr.numMoves + 1, curr));
                    }
                }
            }
        }
    }

    /**
     * @return The minimum number of moves required to solve the puzzle
     */
    public int moves() {
        return goalNode.numMoves;
    }

    /**
     * @return A sequence of WorldStates from the initial one to the solution
     */
    public Iterable<WorldState> solution() {
        ArrayList<WorldState> solution = new ArrayList<>(goalNode.numMoves + 1);
        SearchNode curr = goalNode;
        for (int i = goalNode.numMoves; i >= 0; i--) {
            solution.add(curr.state);
            curr = curr.prev;
        }

        Collections.reverse(solution);

        return solution;
    }
}
