1. Functional interfaces like 
   1. Function: R apply(T t), 
   2. Supplier: T get(),
   3. Predicate: boolean test(T t), 
   4. Consumer: void accept(T t), 
   5. BinaryOperator: T apply(T t1, T t2). It extends BiFunction: R apply(T t, U u)
2. Lambdas
   1. forEach and filter 
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