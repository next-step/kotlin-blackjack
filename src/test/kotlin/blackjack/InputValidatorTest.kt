package blackjack

import blackjack.domain.InputValidator
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class InputValidatorTest {
    @ValueSource(strings = ["a", "aa", " "])
    @ParameterizedTest
    fun `y, n이 아닌것을 입력했을때 예외발생하는지 테스트`(input: String) {
        assertThrows<IllegalArgumentException> {
            InputValidator.checkYorN(input)
        }
    }
}
