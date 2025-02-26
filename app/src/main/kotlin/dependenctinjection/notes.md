- Many times, classes require objects of other classes to function. When a class requires another class, the required class is called a dependency.
- To make the code more flexible and adaptable, a class must not instantiate the objects it depends on. The objects it depends on must be instantiated outside the class and then passed in. This approach creates more flexible code, as the class is no longer hardcoded to one particular object. The implementation of the required object can change without needing to modify the calling code.
- Passing in the required objects is called dependency injection (DI). It is also known as inversion of control.

- DI is when a dependency is provided at runtime instead of being hardcoded into the calling class.

- Implementing dependency injection:

- Helps with the reusability of code. Code is not dependent on a specific object, which allows for greater flexibility.
- Makes refactoring easier. Code is loosely coupled, so refactoring one section of code does not impact another section of code.
- Helps with testing. Test objects can be passed in during testing
