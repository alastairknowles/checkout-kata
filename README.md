**Checkout Kata Application**

Very small application demonstrating how the code for a checkout in an electronic point of sale system could be written.

There is currently no external interface to the system, as it is assumed that in a real scenario it would be installed as a driver. A rest API (for example) would be of no obvious use.

In writing the application it was assumed that:

* There is no reason to address concurrency or synchronisation concerns as only one customer can checkout at once.
* Default pricing would be provided by way of a database link, which is mocked in the application.
* Override pricing would be supplied a the time of checkout - analogous to 'marked down' items in a shop.

The application can be verified by running the tests. Either launch it in an IDE, or cd into the project folder, and ./gradlew build.