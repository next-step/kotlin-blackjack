package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import java.lang.IllegalArgumentException

class CardNumberTest {
    @Test
    fun `번호를 가진다`() {
        val cardNumber = CardNumber(value = 1)

        assertThat(cardNumber.value).isEqualTo(1)
    }

    @ParameterizedTest
    @ValueSource(ints = [0, 14])
    fun `범위를 벗어나는 번호를 받으면 에러를 일으킨다`(invalidNumber: Int) {
        assertThrows<IllegalArgumentException> {
            CardNumber(invalidNumber)
        }
    }
}
