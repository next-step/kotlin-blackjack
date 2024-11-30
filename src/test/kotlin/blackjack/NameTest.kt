package blackjack

import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource

class NameTest {
    @ParameterizedTest
    @EmptySource
    fun `공백 일 수 없다`(value: String) {
        assertThatIllegalArgumentException().isThrownBy {
            Name(value)
        }
    }
}