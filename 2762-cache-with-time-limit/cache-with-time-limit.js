var TimeLimitedCache = function() {
    this.map = new Map();
};

/** 
 * @param {number} key
 * @param {number} value
 * @param {number} duration time until expiration in ms
 * @return {boolean} if un-expired key already existed
 */
TimeLimitedCache.prototype.set = function(key, value, duration) {
    const currentTime = Date.now();
    let exists = false;

    if (this.map.has(key)) {
        const [oldValue, expiry] = this.map.get(key);
        if (expiry > currentTime) {
            exists = true;
        }
    }

    const expiryTime = currentTime + duration;
    this.map.set(key, [value, expiryTime]);

    return exists;
};

/** 
 * @param {number} key
 * @return {number} value associated with key
 */
TimeLimitedCache.prototype.get = function(key) {
    const currentTime = Date.now();

    if (this.map.has(key)) {
        const [value, expiry] = this.map.get(key);

        if (expiry > currentTime) {
            return value;
        } else {
            this.map.delete(key); // cleanup expired
        }
    }

    return -1;
};

/** 
 * @return {number} count of non-expired keys
 */
TimeLimitedCache.prototype.count = function() {
    const currentTime = Date.now();
    let count = 0;

    for (const [key, [value, expiry]] of this.map) {
        if (expiry > currentTime) {
            count++;
        } else {
            this.map.delete(key); // cleanup expired
        }
    }

    return count;
};

/**
 * const timeLimitedCache = new TimeLimitedCache()
 * timeLimitedCache.set(1, 42, 1000); // false
 * timeLimitedCache.get(1) // 42
 * timeLimitedCache.count() // 1
 */