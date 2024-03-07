1. Collection vs Stream
   Collection is an in-memory data structure, which holds all the values that the data structure currently has.
   Stream API is used to process collections of objects. A stream is a sequence of objects that supports various methods which can be pipelined to produce the desired result.
   1. A stream is not a data structure instead it takes input from the Collections, Arrays, or I/O channels.
   2. Streams don’t change the original data structure, they only provide the result as per the pipelined methods.
   3. Each intermediate operation is lazily executed and returns another stream as a result, hence various intermediate operations can be pipelined. Terminal operations mark the end of the stream and return the result.
2. Collector interface: reduction operations
   1. Custom collector
3. Functional interfaces: single abstract method 
   1. Function: R apply(T t), 
   2. Supplier: T get(),
   3. Predicate: boolean test(T t), 
   4. Consumer: void accept(T t), 
   5. BinaryOperator: T apply(T t1, T t2). It extends BiFunction: R apply(T t, U u)
2. Lambdas : express instances of FI, implement only abstract function
   1. forEach and filter 
   2. Functionalities of lambda fn:
      1. Enable to treat functionality as method argument, or code as data
      2. Function created without belonging to any class
      3. Lambda expr can be passed as an object and executed on demand => lambdas are lazy
   3. Only use variable that are effectively final
3. Arrays.sort using TimSort
4. Collections class changes in java 1.8 like checkedMap (returns a dynamically typesafe view of the specified map) 
5. Map
   1. Differences between map and flatmap

| map                                                             | flatmap                                                            |
|-----------------------------------------------------------------|--------------------------------------------------------------------|
| processes stream of values                                      | stream of stream of values                                         |
| mapping                                                         | mapping+flattening                                                 |
| one-to-one mapping                                              | one-to-many mapping                                                |
| data transformation from stream to stream                       | data transformation from stream<stream> to stream                  |
| it's mapper function produces single value for each input value | it's mapper function produces multiple values for each input value |
   
6. List
   1. sorting
7. Streams API
   1. reduce : Stream.of(1,2,3,4).reduce(identity, accumulator)
   2. parallel streams
8. Optional : to avoid NPE, public final class
   1. to create instance use static methods: of(T value),empty(), ofNullable(T value) 