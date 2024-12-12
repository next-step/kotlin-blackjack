package blackjack.core.player

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class NameTest {
    @ParameterizedTest
    @CsvSource(
        "'abc', 'abc'",
        "'  abc', 'abc'",
        "'abc  ', 'abc'",
    )
    fun `앞뒤 빈글짜 있는 문자열이 전달되었을 때 Class 생성을 테스트`(
        source: String,
        result: String,
    ) {
        Name(source).toString() shouldBe result
    }

    @ParameterizedTest
    @CsvSource("' '", "''", "'      '")
    fun `공백가 전달되었을 때 Class 생성 오류를 테스트`(source: String) {
        shouldThrow<IllegalArgumentException> { Name(source) }
    }
}
