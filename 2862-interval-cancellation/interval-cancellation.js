/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
var cancellable = function(fn, args, t) {
    // Call immediately at time = 0
    fn(...args);

    // Start repeated execution
    const intervalId = setInterval(() => {
        fn(...args);
    }, t);

    // Cancel function
    return function cancelFn() {
        clearInterval(intervalId);
    };
};