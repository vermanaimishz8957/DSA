/**
 * @param {Array<Function>} functions
 * @return {Promise<any>}
 */
var promiseAll = function(functions) {
    return new Promise((resolve, reject) => {
        const results = new Array(functions.length);
        let completed = 0;

        functions.forEach((fn, index) => {
            fn()
                .then((value) => {
                    results[index] = value; // maintain order
                    completed++;

                    if (completed === functions.length) {
                        resolve(results);
                    }
                })
                .catch((err) => {
                    reject(err); // reject immediately on first error
                });
        });
    });
};

/**
 * const promise = promiseAll([() => new Promise(res => res(42))])
 * promise.then(console.log); // [42]
 */