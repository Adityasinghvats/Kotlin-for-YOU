- When a coroutine launches another coroutine, the job that returns from the new coroutine is called the child of the original parent job.
- Coroutines are typically launched into a CoroutineScope. This ensures that we don't have coroutines that are unmanaged and get lost, which could waste resources.

- launch() and async() are extension functions on CoroutineScope. Call launch() or async() on the scope to create a new coroutine within that scope.

- A CoroutineScope is tied to a lifecycle, which sets bounds on how long the coroutines within that scope will live. If a scope gets cancelled, then its job is cancelled, and the cancellation of that propagates to its child jobs. If a child job in the scope fails with an exception, then other child jobs get cancelled, the parent job gets cancelled, and the exception gets re-thrown to the caller.
- runblocking() also launches a coroutine scope.
- If you check the source code for how CoroutineScope.kt is implemented in the Kotlin coroutines library, you can see that CoroutineScope is declared as an interface and it contains a CoroutineContext as a variable.

- The launch() and async() functions create a new child coroutine within that scope and the child also inherits the context from the scope. What is contained within the context? Let's discuss that next.
- 