package blackjack.domain

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

class EntrantNameTest {
    @ParameterizedTest
    @EmptySource
    fun `공백 일 수 없다`(value: String) {
        assertThatIllegalArgumentException().isThrownBy {
            EntrantName(value)
        }
    }
}
