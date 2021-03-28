package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class CardTest {
    @ParameterizedTest
    @CsvSource(
        "SPADES, ACE",
        "HEARTS, KING"
    )
    internal fun `카드는 문양 하나와 눈 하나를 가진다`(suit: Suit, symbol: Symbol) {
        val card = Card(suit = suit, symbol = symbol)

        assertAll(
            { assertThat(card.suit).isEqualTo(suit) },
            { assertThat(card.symbol).isEqualTo(symbol) }
        )
    }
}
