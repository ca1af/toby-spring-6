package com.example.tobyspring6;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SortTest {
    private Sort sort;

    @BeforeEach
    void setUp() {
        sort = new Sort();
    }

    @Test
    void sort() {
        // arrange
        List<String> givenList = Arrays.asList("aa", "b");

        // act
        List<String> list = sort.sortByLength(givenList);

        // assert
        assertThat(list).isEqualTo(List.of("b", "aa"));
    }

    // 가설을 하나 준비하고, 검증한다. 그러나 충분치 않은 경우 더 많은 테스트로 검증한다.

    @Test
    void sort3Items() {
        // arrange
        List<String> givenList = Arrays.asList("aa", "ccc", "b");

        // act
        List<String> list = sort.sortByLength(givenList);

        // assert
        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }

    // 검증이 완료되었더라도 엣지케이스를 생각한다. -> 예시 : 동작을 할 필요가 없었다면?
    @Test
    void sortAlreadySorted() {
        // arrange
        List<String> givenList = Arrays.asList("b", "aa", "ccc");

        // act
        List<String> list = sort.sortByLength(givenList);

        // assert
        assertThat(list).isEqualTo(List.of("b", "aa", "ccc"));
    }
}