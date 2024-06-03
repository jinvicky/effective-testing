package com.jvk.tddeffective;

import com.jvk.model.Book;
import net.jqwik.api.*;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BookTest {

    @Property
    void differentBooks(@ForAll("books") Book book) {
        // 다른 책이다!
        System.out.println(book);

        // 테스트 코드 작성하기
    }

    @Provide
    Arbitrary<Book> books() {
        // 책 클래스의 각 필드에 대해 임의값을 생성할 Arbitrary 인스턴스
        Arbitrary<String> titles = Arbitraries.strings().withCharRange('a', 'z')
                .ofMinLength(10).ofMaxLength(100);
        Arbitrary<String> authors = Arbitraries.strings().withCharRange('a', 'z')
                .ofMinLength(5).ofMaxLength(21);
        Arbitrary<Integer> qtyOfPages = Arbitraries.integers().between(0, 450);

        // 이를 조합해서 책 인스턴스를 생성한다.
        return Combinators.combine(titles, authors, qtyOfPages)
                .as((title, author, pages) -> new Book(title, author, pages));
    }

}
