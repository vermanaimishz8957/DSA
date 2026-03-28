class Solution {
    public String findTheString(int[][] lcp) {
        int n = lcp.length;
        char s[] = new char[n];

        char ch = 'a';
        for(int i=0; i<n; i++) {

            if(s[i] > 0)
                continue;
            if(ch > 'z')
                return "";
            s[i] = ch;
            for(int j=i+1; j<n; j++) {
                if(lcp[i][j] > 0) {
                    s[j] = s[i];
                }
            }
            ch++;
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                int prefix;
                if(s[i]==s[j]) {
                    int prevpref = 0;
                    if(i==n-1 || j==n-1)
                        prevpref = 0;
                    else prevpref = lcp[i+1][j+1];
                    prefix = 1+prevpref;
                }
                else prefix = 0;
                if(prefix!=lcp[i][j])
                    return"";
            }
        }

        return String.valueOf(s);
    }
}