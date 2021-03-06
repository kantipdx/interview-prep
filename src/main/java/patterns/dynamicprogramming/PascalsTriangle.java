package patterns.dynamicprogramming;


import java.util.ArrayList;
import java.util.List;

/*
*  https://leetcode.com/problems/pascals-triangle/
* Given an integer numRows, return the first numRows of Pascal's triangle.
In Pascal's triangle, each number is the sum of the two numbers directly above it as shown:
* Example 1:
Input: numRows = 5
Output: [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
* Example 2:
Input: numRows = 1
Output: [[1]]
* */
public class PascalsTriangle {
    /*
    * Time complexity : O(N^2)
    * Space Complexity: O(N^2)
    * */
    public static List<List<Integer>> generate(int numRows){
        List<List<Integer>> triangles = new ArrayList<List<Integer>>();
        triangles.add(new ArrayList<>());
        triangles.get(0).add(1);
        for(int i=1; i<numRows;i++){
            List<Integer> curRow = new ArrayList<>();
            List<Integer> preRow = triangles.get(i-1);
            //First element in the row always 1
            curRow.add(1);
            for(int j=1;j<i;j++){
                curRow.add(preRow.get(j) + preRow.get(j-1));
            }
            //Last element in the row always 1
            curRow.add(1);
            triangles.add(curRow);
        }
        return triangles;
    }

    public static void main(String[] args) {
        System.out.println(generate(5));
    }
}
