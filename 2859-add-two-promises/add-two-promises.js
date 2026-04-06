/**
 * @param {Promise} promise1
 * @param {Promise} promise2
 * @return {Promise}
 */
var addTwoPromises = async function(promise1, promise2) {
    
    // Use Promise.all to run both promises in parallel
    // It waits until both promises are resolved
    // Returns an array of resolved values
    const results = await Promise.all([promise1, promise2]);
    
    // Extract values from the result array
    const val1 = results[0];
    const val2 = results[1];
    
    // Return the sum of the resolved values
    // Since the function is async, it automatically returns a Promise
    return val1 + val2;
};

/**
 * Example:
 * addTwoPromises(Promise.resolve(2), Promise.resolve(2))
 *   .then(console.log); // Output: 4
 */