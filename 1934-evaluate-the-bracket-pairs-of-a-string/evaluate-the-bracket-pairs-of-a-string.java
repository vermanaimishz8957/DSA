class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }
        
        StringBuilder result = new StringBuilder();
        int n = s.length();
        int i = 0;
        
        while (i < n) {
            char c = s.charAt(i);
            if (c == '(') {
                int j = i + 1;
                StringBuilder key = new StringBuilder();
                while (s.charAt(j) != ')') {
                    key.append(s.charAt(j));
                    j++;
                }
                // j is now at the index of ')'
                String value = map.getOrDefault(key.toString(), "?");
                result.append(value);
                i = j + 1; // move past ')'
            } else {
                result.append(c);
                i++;
            }
        }
        
        return result.toString();
    }
}