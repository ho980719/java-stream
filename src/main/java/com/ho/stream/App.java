package com.ho.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) {
        // Functional in nature
        List<String> lists = new ArrayList<>();
        lists.add("junho");
        lists.add("minsong");
        lists.add("sungwon");
        lists.add("hayoung");

        // 소스 변경 불가
        // Stream에서 제공하는 API는 두종류 (중개 오퍼레이션, 종료 오퍼레이션)
        // 중개 = lazy
        // 종료가 오기전까지 중개는 무의미함

        List<String> newLists = lists.stream().map((s -> {
            return s.toUpperCase();
        })).collect(Collectors.toList());

        newLists.forEach(System.out::println);

        System.out.println("------------------");

        lists.forEach(System.out::println);

        System.out.println("------------------");

        List<String> collect = lists.parallelStream().map(String::toUpperCase).collect(Collectors.toList());
        collect.forEach(System.out::println);

    }
}
