/**
 * @param {number} n
 * @return {Function} counter
 */
var createCounter = function(n) {
    let count = n; // store the current count
    return function() {
        return count++; // return current value and increment
    };
};

/** 
 * const counter = createCounter(10)
 * counter() // 10
 * counter() // 11
 * counter() // 12
 */