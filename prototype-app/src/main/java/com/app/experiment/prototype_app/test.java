package com.app.experiment.prototype_app;

import com.app.experiment.prototype_app.service.hello;
import io.swagger.models.auth.In;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class test {
    public static void main(String[] args) {
        hello h = () -> System.out.println("Hi from functional interface");
        h.sayHello();

        //testing the perdicate -- this will return TRUE or FALSE
        Predicate<Integer> p = i -> (i > 10);
        System.out.println(p.test(20));

        //testing function -- this will return a single value
        Function<Integer, Integer> f = i -> i * i;
        Function<Integer, Integer> f1 = i -> i + i;
        System.out.println(f.andThen(f1).apply(2));

        //*** STREAMS ****
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> output = input.stream().filter(i -> i % 2 == 0).collect(Collectors.toList());
        System.out.println(output);


        //*** MAP ****
        List<String> input2 = Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h", "i", "j");
        input2.stream().map(i -> i.toUpperCase()).forEach(System.out::println);

        //testing flatMap

        List<List<String>> input3 = Arrays.asList(
                Arrays.asList("a", "b"),
                Arrays.asList("c", "d"),
                Arrays.asList("e", "f"),
                Arrays.asList("g", "h"),
                Arrays.asList("i", "j")
        );
        System.out.println("*******");
        input3.stream().flatMap(x -> x.stream()).map(i -> i.toUpperCase()).forEach(System.out::println);
        System.out.println("*** PARALLEL STREAM ***");
        input3.parallelStream().flatMap(x -> x.stream()).map(i -> i.toUpperCase()).forEach(System.out::println);
        IntStream.range(1, 10).parallel().map(i -> i * 2).forEach(System.out::println);

        //reduce operation
        System.out.println("*******");
        List<Integer> input4 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(input4.stream().reduce((i, returnVal) -> {return i+returnVal;}).get());
    }
}


