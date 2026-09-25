class Solution {
    private int pos;
    private char[] s;

    public List<String> braceExpansionII(String expression) {
        this.s = expression.toCharArray();
        this.pos = 0;
        Set<String> result = parseExpr();
        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);
        return ans;
    }

    // Handles union: seq (',' seq)*
    private Set<String> parseExpr() {
        Set<String> result = new TreeSet<>(parseSeq());
        while (pos < s.length && s[pos] == ',') {
            pos++; // skip ','
            result.addAll(parseSeq());
        }
        return result;
    }

    // Handles concatenation: term term term ...
    private Set<String> parseSeq() {
        List<Set<String>> factors = new ArrayList<>();
        while (pos < s.length && s[pos] != ',' && s[pos] != '}') {
            factors.add(parseTerm());
        }

        Set<String> result = new HashSet<>();
        result.add("");
        for (Set<String> factor : factors) {
            Set<String> newResult = new HashSet<>();
            for (String prefix : result) {
                for (String word : factor) {
                    newResult.add(prefix + word);
                }
            }
            result = newResult;
        }
        return result;
    }

    // Handles a single atomic piece: '{' expr '}'  OR  a single letter
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        if (s[pos] == '{') {
            pos++; // skip '{'
            result = parseExpr();
            pos++; // skip '}'
        } else {
            result.add(String.valueOf(s[pos]));
            pos++;
        }
        return result;
    }
}