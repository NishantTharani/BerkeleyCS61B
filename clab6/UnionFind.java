import java.util.LinkedList;
import java.util.List;

public class UnionFind {

    // TODO - Add instance variables?
    private int[] parents;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = -1;
        }
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex < 0 || vertex >= parents.length) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int root = find(v1);
        return Math.abs(parents[root]);
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root.
       */
    public int parent(int v1) {
        validate(v1);
        return parents[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Uniting a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if (find(v1) == find(v2)) {
            return;
        }

        int sizeOf1 = sizeOf(v1);
        int sizeOf2 = sizeOf(v2);
        int parentOf1 = find(v1);
        int parentOf2 = find(v2);

        if (sizeOf1 == sizeOf2) {
            parents[parentOf1] = parentOf2;
            parents[parentOf2] -= sizeOf1;
        } else if (sizeOf1 < sizeOf2) {
            parents[parentOf1] = parentOf2;
            parents[parentOf2] -= sizeOf1;
        } else {
            parents[parentOf2] = parentOf1;
            parents[parentOf1] -= sizeOf2;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        int root = parents[vertex];

        if (root < 0) {
            return vertex;
        }

        while (parents[root] >= 0) {
            root = parents[root];
        }

        return root;
    }

}
