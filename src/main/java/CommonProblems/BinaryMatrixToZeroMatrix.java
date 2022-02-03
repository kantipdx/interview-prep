package CommonProblems;

import java.util.*;

/*
* Minimum Number of Flips to Convert Binary Matrix to Zero Matrix
* https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/
* https://leetcode.com/problems/minimum-number-of-flips-to-convert-binary-matrix-to-zero-matrix/discuss/450538/Java-or-Regular-BFS-or-w-well-defined-class-or-simple-to-follow-with-comments
* Given a m x n binary matrix mat. In one step, you can choose one cell and flip it and all the four neighbors of it if
* they exist (Flip is changing 1 to 0 and 0 to 1). A pair of cells are called neighbors if they share one edge.
Return the minimum number of steps required to convert mat to a zero matrix or -1 if you cannot.
A binary matrix is a matrix with all cells equal to 0 or 1 only.
A zero matrix is a matrix with all cells equal to 0.
*
* */
public class BinaryMatrixToZeroMatrix {
    /*
    * The idea is to use BFS by considering:
        Initial matrix as source vertex.
        Zero matrix as destination vertex.
        For each vertex, the adjacent vertices would be all the matrices which can be generated by flipping once in the vertex.
        (There would be m*n such vertices for each matrix).
        For marking a vertex as visited, we use a HashSet.
        The following code covers the idea. I have defined a separate class Config to make things cleaner.
        * Time Complexity - O(2^(mn)). The run time is bounded by (mn)^k where k is the number of steps to convert to zero matrix.
          This in turn is bounded by 2^(mn) which is total possible configurations possible.
    * */
    public static int minFlips(int[][] mat) {
        // Instantiate initial config with zero steps
        Config init = new Config(mat, 0);
        if (init.isDone()) {
            return init.step;
        }

        // configs seen so far
        Set<String> visited = new HashSet<>();
        visited.add( init.toString());

        // BFS
        Queue<Config> q = new LinkedList<>();
        q.add(init);
        while(!q.isEmpty()) {
            Config c = q.poll();
            for(Config next: c.getNeighbours()) { // all configs generated by flipping once
                String nextStr = next.toString();
                if (!visited.contains(nextStr)) { // unvisited
                    if (next.isDone()) // reached solution
                        return next.step;
                    visited.add(nextStr);
                    q.add(next);
                }
            }
        }
        return -1; // not possible to reach destination from source
    }

    public static void main(String[] args) {
        int[][] mat = new int[][]{{0,0},{0,1}};
        System.out.println(minFlips(mat));
        mat = new int[][]{{1,0,0},{1,0,0}};
        System.out.println(minFlips(mat));
    }
}
// Internal class to represent matrix config
class Config {
    int[][] mat;
    int rows;
    int cols;
    int step; // number of steps taken to generate current config from initial matrix

    // constructor
    Config(int[][] mat, int step) {
        this.mat = mat;
        rows = mat.length;
        cols = mat[0].length;
        this.step = step;
    }

    // check if matrix is zero matrix
    public boolean isDone() {
        for (int i=0; i<mat.length; i++) {
            for (int val : mat[i])
                if (val != 0)
                    return false;
        }
        return true;
    }

    // generate all (m*n) possible configs by flipping ONCE in current matrix
    public List<Config> getNeighbours() {
        List<Config> neighbours = new ArrayList<>();
        for (int i=0; i<rows; i++)
            for(int j=0; j<cols; j++)
                neighbours.add(flip(i, j));
        return neighbours;
    }

    // next config by flipping value at (row,col) position (and neighbours)
    private Config flip(int row, int col) {
        // create a new copy of matrix
        int[][] next = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if ((i == row && j == col) || (i == row && j == col+1) || (i == row+1 && j == col) ||
                        (i == row && j == col-1) || (i == row-1 && j == col))
                    next[i][j] = 1 - mat[i][j];
                else
                    next[i][j] = mat[i][j];
            }
        }

        // increment step by one in resultant matrix
        return new Config(next, step+1);
    }

    // generate string of 0s and 1s to represent matrix
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<rows; i++) {
            for (int num: mat[i])
                sb.append(num);
        }
        return sb.toString();
    }
}