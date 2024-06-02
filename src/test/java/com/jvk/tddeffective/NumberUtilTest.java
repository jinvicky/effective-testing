package com.jvk.tddeffective;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest
class NumberUtilTest {

    public List<Integer> add(List<Integer> left, List<Integer> right) {
        if(left == null || right == null) {
            return null;
        }
        Collections.reverse(left);
        Collections.reverse(right);

        LinkedList<Integer> result = new LinkedList<>();
        int carry = 0;
        for(int i = 0; i < Math.max(left.size(), right.size()); i++) {
            int leftDigit = left.size() > i ? left.get(i) : 0;
            int rightDigit = right.size() > i ? right.get(i) : 0;

            if (leftDigit < 0 || leftDigit > 9 || rightDigit < 0 || rightDigit > 9) {
                throw new IllegalArgumentException();
            }
            int sum = leftDigit + rightDigit + carry;
            result.addFirst(sum % 10);
            carry = sum / 10;
        }
        return result;
    }

    @Test
    void contextLoads() {
    }

    @ParameterizedTest
    @MethodSource("testCases")
    void shouldReturnCorrectResult(List<Integer> left, List<Integer> right, List<Integer> expected) {
        assertThat(add(left, right)).isEqualTo(expected);
    }

    static Stream<Arguments> testCases() {
        return Stream.of(
                arguments(null, numbers(7,2), null),
                arguments(numbers(), numbers(7,2), numbers(7,2)),
                arguments(numbers(9,8), null, null),
                arguments(numbers(9,8), numbers(), numbers(9,8)),

                arguments(numbers(1), numbers(2), numbers(3)),
                arguments(numbers(9), numbers(2), numbers(1,1)),

                arguments(numbers(2,2), numbers(3,3), numbers(5,5)),
                arguments(numbers(2,9), numbers(2,3), numbers(5,2)),
                arguments(numbers(2,9,3), numbers(1,8,3), numbers(4,7,6)),
                arguments(numbers(1,7,9), numbers(2,6,8), numbers(4,4,7)),
                arguments(numbers(1,9,1,7,1), numbers(1,8,1,6,1), numbers(3,7,3,3,2)),
                arguments(numbers(9,9,8), numbers(1,7,2), numbers(1,1,7,0)),

                arguments(numbers(2,2), numbers(3), numbers(2,5)),
                arguments(numbers(3), numbers(2,2), numbers(2,5)),
                arguments(numbers(2,2), numbers(9), numbers(3,1)),
                arguments(numbers(9), numbers(2,2), numbers(3,1)),
                arguments(numbers(1,7,3), numbers(9,2), numbers(2,6,5)),
                arguments(numbers(9,2), numbers(1,7,3), numbers(2,6,5)),

                arguments(numbers(3,1,7,9), numbers(2,6,8), numbers(3,4,4,7)),
                arguments(numbers(2,6,8), numbers(3,1,7,9), numbers(3,4,4,7)),
                arguments(numbers(1,9,1,7,1), numbers(2,1,8,1,6,1), numbers(2,3,7,3,3,2)),
                arguments(numbers(2,1,8,1,6,1), numbers(1,9,1,7,1), numbers(2,3,7,3,3,2)),
                arguments(numbers(9,9,8), numbers(9,1,7,2), numbers(1,0,1,7,0)),
                arguments(numbers(9,1,7,2), numbers(9,9,8), numbers(1,0,1,7,0)),

                arguments(numbers(0,0,0,1,2), numbers(0,2,3), numbers(3,5)),
                arguments(numbers(0,0,0,1,2), numbers(0,2,9), numbers(4,1)),

                arguments(numbers(9,9), numbers(1), numbers(1,0,0))
        );
    }

    private static List<Integer> numbers(int... numbers) {
        List<Integer> list = new ArrayList<>();
        for(int n : numbers) {
            list.add(n);
        }
        return list;
    }
}
