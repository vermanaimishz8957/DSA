/**
 * @param {Function} fn
 * @param {Array} args
 * @param {number} t
 * @return {Function}
 */
var cancellable = function(fn, args, t) {
    
    // Schedule execution of fn after t ms
    const timerId = setTimeout(() => {
        fn(...args);
    }, t);

    // Return cancel function
    return function cancelFn() {
        clearTimeout(timerId);
    };
};