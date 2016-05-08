# rubix-verifier
Verifier service for the rubix project

# Useful links
* [async methods in spring](http://www.leveluplunch.com/java/tutorials/026-asynchronous-native-java-method-calls-spring/)
* [async configuration](http://www.baeldung.com/spring-async)
* [task executor](http://stackoverflow.com/questions/2269126/using-spring-threading-and-taskexecutor-how-do-i-know-when-a-thread-is-finished?rq=1) 
* [completable futures](http://www.infoq.com/articles/Functional-Style-Callbacks-Using-CompletableFuture)
* [callback style](http://stackoverflow.com/questions/826212/java-executors-how-to-be-notified-without-blocking-when-a-task-completes#answer-22363245)
* [deferred results](http://xpadro.blogspot.de/2015/07/understanding-callable-and-spring.html)
* [deferred result code sample](https://github.com/spring-projects/spring-mvc-showcase/blob/master/src/main/java/org/springframework/samples/mvc/async/DeferredResultController.java)
* [higher performance with non-blocking REST](http://callistaenterprise.se/blogg/teknik/2014/04/22/c10k-developing-non-blocking-rest-services-with-spring-mvc/)
* [checking deferred results before writing](http://stackoverflow.com/questions/32130249/spring-deferredresult-causes-ioexception-an-established-connection-was-aborted/32231627#32231627) - not really needed since setResult() calls it internally
* [deferred results queueing and comparison to regular approach](http://notpurelytechnical.com/spring-3-2-rundown-async-support/)
* [logging recommendations](http://kielczewski.eu/2014/12/few-recommendations-on-logging/)
* [logging with profiles](http://stackoverflow.com/a/25093568/5173530)
* [testing async](http://callistaenterprise.se/blogg/teknik/2014/06/23/testing-non-blocking-rest-services-with-spring-mvc-and-spring-boot/)
* [nice sample code grep](http://www.programcreek.com/java-api-examples/index.php?api=java.util.concurrent.CompletableFuture)