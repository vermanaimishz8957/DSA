/**
 * @param {Function} fn
 * @return {Function}
 */
function memoize(fn) {
    const cache = new Map();
    let callCount = 0;

    function memoized(...args) {
        const key = JSON.stringify(args);

        if (cache.has(key)) {
            return cache.get(key);
        }

        callCount++; // increment only when actual function is called
        const result = fn(...args);
        cache.set(key, result);
        return result;
    }

    memoized.getCallCount = function () {
        return callCount;
    };

    return memoized;
}