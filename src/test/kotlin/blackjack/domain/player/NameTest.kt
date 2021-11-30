package blackjack.domain.player

import org.assertj.core.api.Assertions
import org.assertj.core.api.ThrowableAssert
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

class NameTest {

    @ParameterizedTest
    @EmptySource
    fun `빈 문자열을 이름으로 사용할 수 없다`(value: String) {
        // when
        val callable = ThrowableAssert.ThrowingCallable { Name.from(value) }

        // then
        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy(callable)
            .withMessageMatching("이름은 null 또는 빈문자열일 수 없습니다.")
    }
}
