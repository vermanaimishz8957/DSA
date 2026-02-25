class Solution {
    public int[] sortByBits(int[] arr) {
        
        Integer[] temp = new Integer[arr.length];
        
        // Convert int[] to Integer[]
        for (int i = 0; i < arr.length; i++) {
            temp[i] = arr[i];
        }
        
        // Sort using custom comparator
        java.util.Arrays.sort(temp, (a, b) -> {
            int countA = Integer.bitCount(a);
            int countB = Integer.bitCount(b);
            
            if (countA != countB) {
                return countA - countB;  // Sort by number of set bits
            }
            return a - b;               // If equal bits, sort numerically
        });
        
        // Copy back to original array
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp[i];
        }
        
        return arr;
    }
}