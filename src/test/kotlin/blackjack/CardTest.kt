package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CardTest {
    @ParameterizedTest
    @CsvSource("EIGHT, DIAMOND", "KING, HEART", "ACE, CLOVER")
    fun `카드는 문양과 숫자를 가지고 있다`(number: CardNumber, suite: CardSuite) {
        val card = Card(suite, number)

        assertAll(
            { assertThat(card.number).isEqualTo(number) },
            { assertThat(card.suite).isEqualTo(suite) }
        )
    }
}
