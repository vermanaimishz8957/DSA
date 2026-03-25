function createHelloWorld() {
    // Return a new function
    return function(...args) {
        // Ignore any arguments and always return "Hello World"
        return "Hello World";
    }
}