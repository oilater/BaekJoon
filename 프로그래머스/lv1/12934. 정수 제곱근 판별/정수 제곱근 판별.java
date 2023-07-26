class Solution {
    public long solution(long n) {
        double a = Math.sqrt(n);
        return a == Math.floor(a) ? (long) Math.pow( a +1 , 2) : -1;
    }
}