package com.ho.javapractice;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.*;
import java.util.stream.Stream;

public class Foo {
    static Calculator sum =  (a, b) -> a + b;
    static Calculator subtract =  (a, b) -> a - b;
    static Calculator multiply =  (a, b) -> a * b;
    static Calculator divide =  (a, b) -> a / b;

    public static void main(String[] args) {
        Foo foo = new Foo();

        foo.methodReference();
    }

    private void methodReference() {
        // 기존에는 inline으로 구현을 진행
        Function<Integer, String> intToString = i -> "this number is " + i.toString();
        System.out.println(intToString.apply(4));

        // 메소드 레퍼런스
        // 스태틱 메소드 참조
        UnaryOperator<String> hello = Greeting::hello;
        System.out.println(hello.apply("kim jun ho"));;

        // 생성자 참조
        Supplier<Greeting> supplier = Greeting::new;
        supplier.get();

        Greeting greeting = new Greeting();
        greeting.hi("junho");


        Function<String, Greeting> testGreeting = Greeting::new;
        Supplier<Greeting> newGreeting = Greeting::new;

        String[] names = {"kim", "lee", "adam"};
//        Arrays.sort(names);
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));

    }

    private void shadowing() {
        int baseNumber = 10 ;

        // local class
        class LocalClass {
            void printBaseNumber() {
                int baseNumber = 11;
                System.out.println(baseNumber); // 11
            }
        }
        // anonymous class
        Consumer<Integer> integerConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                int baseNumber = 13;
                System.out.println(baseNumber); // 13
            }
        };
        // lambda
        // 람다의 경우 같은 스코프 영역으로 취급된다
        Consumer<Integer> integerConsumerLambda = (i) -> {
//            int baseNumber = 20; // compile error
            System.out.println(baseNumber); // effective final
        };
    }

    private void memo() {
        Function<Integer, Integer> plus10 = (a) -> a + 10;
        Function<Integer, Integer> multiply2 = (a) -> a * 2;

        System.out.println(plus10.apply(15));
        System.out.println(multiply2.apply(15));
        // Higher Order Function
        Function<Integer, Integer> plusBeforeMultiply = plus10.compose(multiply2);
        Function<Integer, Integer> plusAfterMultiply = plus10.andThen(multiply2);
        System.out.println(plusBeforeMultiply.apply(15));
        System.out.println(plusAfterMultiply.apply(15));

        Consumer<Integer> printT = i -> System.out.println(i);
        printT.accept(15);

        Supplier<Integer> supplier = () -> 10;
        System.out.println(supplier.get());

        Predicate<String> predicate = s -> s.startsWith("kim");
        System.out.println(predicate.test("kimkim"));
        System.out.println(predicate.test("junjun"));

        UnaryOperator<Integer> plus20 = i -> i + 20;
        plus20.apply(30);

        BinaryOperator<Long> sum = (Long a, Long b) -> a + b;
    }
}
