package com.jvk.tddeffective;

import com.jvk.model.CountWords;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CountWordsTest {

    /**
     * 예제 3.2 CountWords에 관한 1번째(불완전한) 테스트
     */
    @Test
    void twoWordsEndWithS() {
        int words = new CountWords().count("dogs cats");
        assertThat(words).isEqualTo(2);
    }

    @Test
    void noWordsAtAll() {
        int words = new CountWords().count("dog cat");
        assertThat(words).isEqualTo(0);
    }

    @Test
    void wordsThatEndInR() {
        int words = new CountWords().count("car bar");
        assertThat(words).isEqualTo(2);
    }

}
