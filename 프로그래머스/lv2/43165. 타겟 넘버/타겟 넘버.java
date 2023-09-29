import java.io.*;
import java.util.*;

class Solution {
    static int cnt;
    public int solution(int[] numbers, int target) throws IOException {
        
        dfs(numbers, target, 0, 0);
        return cnt;
    }
    
    
    private static void dfs(int[] numbers, int target, int idx, int sum) {
        if (idx == numbers.length) {
            if (sum == target) cnt++;
            return;
        }
        
        dfs(numbers, target, idx + 1, sum + numbers[idx]);
        dfs(numbers, target, idx + 1, sum - numbers[idx]);
    }
}