import java.util.*;

class Fancy {

    long MOD = 1000000007;
    ArrayList<Long> list;
    long mul;
    long add;

    public Fancy() {
        list = new ArrayList<>();
        mul = 1;
        add = 0;
    }
    
    public void append(int val) {
        long x = ((val - add) % MOD + MOD) % MOD;
        x = (x * modInverse(mul)) % MOD;
        list.add(x);
    }
    
    public void addAll(int inc) {
        add = (add + inc) % MOD;
    }
    
    public void multAll(int m) {
        mul = (mul * m) % MOD;
        add = (add * m) % MOD;
    }
    
    public int getIndex(int idx) {
        if (idx >= list.size()) return -1;
        
        long val = list.get(idx);
        long ans = (val * mul) % MOD;
        ans = (ans + add) % MOD;
        
        return (int) ans;
    }

    private long modInverse(long x) {
        return power(x, MOD - 2);
    }

    private long power(long a, long b) {
        long res = 1;
        a %= MOD;
        
        while (b > 0) {
            if ((b & 1) == 1)
                res = (res * a) % MOD;
            
            a = (a * a) % MOD;
            b >>= 1;
        }
        
        return res;
    }
}