package com.ho.javapractice;

@FunctionalInterface
public interface RunSomething {
    // 추상 메소드를 하나만 가지고 있는 인터페이스 == 함수형 인터페이스
    String doIt(String name);

}
