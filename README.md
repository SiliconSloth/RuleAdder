### Rule Based Symbolic Arithmetic with Drools

This is a fairly simple program to help me learn Drools.
It takes two positive integers from the command line, and adds them.

This is implemented by converting each input number to a sequence of digit symbols,
which the rules manipulate to perform addition directly on these symbols,
without using the `+` operator. This is similar to humans counting symbolically on their fingers.

This is of course a very impractical and contrived use of Drools,
which could be solved far more easily with a single line of code.
However, it does show a form of crude backward chaining in Drools, which is usually a forward chaining system.
When a computation needs the result of another computation, rather than calling a function
and getting a return value like most code would, this system sends a request for that information,
and another rule is fired once the result is available.
This has the advantage that values can be cached and reused, which could be useful in systems
where a lot of separate processes are working in the some domain, so might want to share common
function results automatically, without explicit caching data structures.