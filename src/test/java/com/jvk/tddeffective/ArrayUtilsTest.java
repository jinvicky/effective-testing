package com.jvk.tddeffective;

import com.jvk.model.ArrayUtils;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.of;

@SpringBootTest
class ArrayUtilsTest {

   @ParameterizedTest
   @MethodSource("testCases")
   void testIndexOf(int[] array, int valueToFind, int startIndex, int expectedResult) {
       int result = ArrayUtils.indexOf(array, valueToFind, startIndex);
       assertThat(result).isEqualTo(expectedResult);
   }

   static Stream<Arguments> testCases() {
       int[] array = new int[] {1,2,3,4,5,4,6,7};

       return Stream.of(
               of(null, 1,1,-1), // array가 null인 경우

               of(new int[] {1}, 1, 0, 0), // array의 요소가 1개이고, array에 valueToFind가 있는 경우
               of(new int[] {1}, 2, 0, -1), // array의 요소가 1개이고, array에 valueToFind가 없는 경우

               of(array, 1, 10, -1), // startIndex가 음수이고, array에 값이 있는 경우
               of(array, 2, -1, 1), // startIndex가 array의 범위 밖에 있는 경우
               of(array, 4, 6, -1), // array 요소가 다수이고, arraydp valueToFind가 있으며, startIndex가 valueToFind 이후인 경우
               of(array, 4, 1, 3), // array 요소가 다수이고, array에 valueToFind가 있으며, startIndex가 valueToFind 이전인 경우
               of(array, 4, 3, 3), // array " 가 다수이고, array에 valueToFind가 있으며, startIndex가 valueToFind의 정확한 위치인 경우
               of(array, 4, 1, 3), // array " 가 다수이고 array에 valueToFind가 여러 개 있으며, startIndex가 valueToFind 이전인 경우
               of(array, 4, 4, 5), // array " 가 다수이고 array에 valueToFind가 여러 개 있으며, 그 중 하나가 startIndex 이전에 있는 경우
               of(array, 8, 0, -1) // array 요소가 다수이고 array에 valueToFind가 없는 경우
       );
   }

    /**
     * 예제 5.10 라이브러리에 있는 실제 테스트 스위트(http://mng.bz/aDAY).
     */
    @Test
    public void testIndexOfIntWithStartIndex() {
        int[] array = null;
        assertEquals(-1, ArrayUtils.indexOf(array, 0, 2));

        array = new int[]{0,1,2,3,0};
        assertEquals(4, ArrayUtils.indexOf(array, 0, 2));

        assertEquals(-1, ArrayUtils.indexOf(array, 1, 2));

        assertEquals(2, ArrayUtils.indexOf(array, 2, 2));

        assertEquals(3, ArrayUtils.indexOf(array, 3, 2));

        assertEquals(3, ArrayUtils.indexOf(array, 3, -1));

        assertEquals(-1, ArrayUtils.indexOf(array, 99, 0));

        assertEquals(-1, ArrayUtils.indexOf(array, 0, 6));
    }

    /**
     * 예제 5.11 indexOf에 대한 속성 기반 테스트
     */
    @Property
    void indexOfShouldFindFirstValue(
            @ForAll
            @Size(value = 100) List<@IntRange(min = -1000, max = 1000) Integer> numbers,  // 무작위 정수 리스트
            @ForAll
            @IntRange(min = 1001, max = 2000) int value,
            @ForAll
            @IntRange(max = 99) int indexToAddElement,
            @ForAll
            @IntRange(max = 99) int startIndex) {
        numbers.add(indexToAddElement, value);

        int[] array = convertListToArray(numbers);

        int expectedIndex = indexToAddElement >= startIndex ?
                indexToAddElement : -1;

        assertThat(ArrayUtils.indexOf(array, value, startIndex))
                .isEqualTo(expectedIndex);
    }

    private int[] convertListToArray(List<Integer> numbers) {
        int[] array = numbers.stream().mapToInt(x -> x).toArray();
        return array;
    }

}
