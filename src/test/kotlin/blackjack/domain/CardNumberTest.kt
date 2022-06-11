package blackjack.domain

import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

/**
 * Created by Jaesungchi on 2022.06.11..
 */
class CardNumberTest {
    @ParameterizedTest
    @ValueSource(strings = ["q", "22", "11"])
    fun `카드숫자에 없는 숫자를 가져오는 경우 IllegalArgumentException을 던진다`(source: String) {
        assertThrows<IllegalArgumentException> {
            CardNumber.pick(source)
        }
    }
}
