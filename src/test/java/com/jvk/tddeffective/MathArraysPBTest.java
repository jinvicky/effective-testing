package com.jvk.tddeffective;

import com.jvk.model.MathArrays;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MathArraysPBTest {

    @Property
    void unique(
            @ForAll
            @Size(value = 100)
            List<@IntRange(min = 1, max = 20) Integer>
            numbers) {

        int[] doubles = convertListToArray(numbers);
        int[] result = MathArrays.unique(doubles);

        assertThat(result)
                .contains(doubles)
                .doesNotHaveDuplicates()
                .isSortedAccordingTo(Comparator.reverseOrder());

    }

    private int[] convertListToArray(List<Integer> numbers) {
        int[] array = numbers
                .stream()
                .mapToInt(x -> x)
                .toArray();
        return array;
    }
}
