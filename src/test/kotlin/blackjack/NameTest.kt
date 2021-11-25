package blackjack

import blackjack.domain.Name
import org.assertj.core.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class NameTest {

    @ParameterizedTest
    @ValueSource(strings = [" ", "  ", "   "])
    fun `이름에 공백을 입력하면 Exception이 발생한다`(emptyName: String) {
        Assertions.assertThatExceptionOfType(IllegalArgumentException::class.java).isThrownBy {
            Name.from(emptyName)
        }.withMessage(Name.NAME_MUST_NOT_BLANK_MESSAGE)
    }
}
