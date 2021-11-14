package ds.dstype.hashtable;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    /*

Write an algorithm to determine if a number n is happy.

A happy number is a number defined by the following process:
Starting with any positive integer, replace the number by the sum of the squares of its digits.
Repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does not include 1.
Those numbers for which this process ends in 1 are happy.
Return true if n is a happy number, and false if not.
* */
    //Approach 1: Detect Cycles with a HashSet
    public static boolean isHappy(int n){
        Set<Integer> seenNums = new HashSet<>();
        while (n != 1 && !seenNums.contains(n)){
            seenNums.add(n);
            n = getNextNum(n);
        }
        return n == 1;
    }
    public static int getNextNum(int n){
        int totalSum = 0;
        while (n > 0){
            int digit = n % 10;
            n = n/10;
            totalSum += digit * digit;
        }
        return totalSum;
    }
    /*
    Complexity Analysis
    Determining the time complexity for this problem is challenging for an "easy" level question. If you're new to these
    problems, have a go at calculating the time complexity for just the getNext(n) function (don't worry about how many numbers will be in the chain).

    Time complexity : O(243 \cdot 3 + \log n + \log\log n + \log\log\log n)...O(243⋅3+logn+loglogn+logloglogn)... = O(\log n)O(logn).

    Finding the next value for a given number has a cost of O(\log n)O(logn) because we are processing each digit in the
    number, and the number of digits in a number is given by \log nlogn.

    To work out the total time complexity, we'll need to think carefully about how many numbers are in the chain, and how
    big they are.

    We determined above that once a number is below 243243, it is impossible for it to go back up above 243243. Therefore,
    based on our very shallow analysis we know for sure that once a number is below 243243, it is impossible for it to take
    more than another 243243 steps to terminate. Each of these numbers has at most 3 digits. With a little more analysis,
    we could replace the 243243 with the length of the longest number chain below 243243, however because the constant
    doesn't matter anyway, we won't worry about it.

    For an nn above 243243, we need to consider the cost of each number in the chain that is above 243243. With a little
    math, we can show that in the worst case, these costs will be O(\log n) + O(\log \log n) + O(\log \log \log n)...O(logn)+O(loglogn)+O(logloglogn).... Luckily for us, the O(\log n)O(logn) is the dominating part, and the others are all tiny in comparison (collectively, they add up to less than \log n)logn), so we can ignore them.

    Space complexity : O(\log n)O(logn). Closely related to the time complexity, and is a measure of what numbers we're
    putting in the HashSet, and how big they are. For a large enough nn, the most space will be taken by nn itself.

    We can optimize to O(243 \cdot 3) = O(1)O(243⋅3)=O(1) easily by only saving numbers in the set that are less than 243243,
    as we have already shown that for numbers that are higher, it's impossible to get back to them anyway.

    It might seem worrying that we're simply dropping such "large" constants. But this is what we do in Big O notation,
    which is a measure of how long the function will take, as the size of the input increases.

    Think about what would happen if you had a number with 1 million digits in it. The first step of the algorithm would
    process those million digits, and then the next value would be, at most (pretend all the digits are 9), be 81 * 1,000,000 = 81,000,00081∗1,000,000=81,000,000.
    In just one step, we've gone from a million digits, down to just 8. The largest possible 8 digit number we could get is 99,9999,99999,9999,999,
    which then goes down to 81 * 8 = 64881∗8=648. And then from here, the cost will be the same as if we'd started with a 3 digit number.
    Starting with 2 million digits (a massively larger number than one with a 1 million digits) would only take roughly twice as long, as again,
    the dominant part is summing the squares of the 2 million digits, and the rest is tiny in comparison.
    * */

    //Approach 2:
    //Floyd's Cycle-Finding Algorithm
    //Fast-runner and slow-runner to find a cycle in linked-list
    public int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            n = n / 10;
            totalSum += d * d;
        }
        return totalSum;
    }

    public boolean isHappyDetectCycle(int n) {
        int slowRunner = n;
        int fastRunner = getNext(n);
        while (fastRunner != 1 && slowRunner != fastRunner) {
            slowRunner = getNext(slowRunner);
            fastRunner = getNext(getNext(fastRunner));
        }
        return fastRunner == 1;
    }
}
