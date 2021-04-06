package blackjack.playingcard

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
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

    @Test
    internal fun `카드는 객체가 달라도 문양과 눈이 같으면 동일하다`() {
        // given
        val suit = Suit.DIAMONDS
        val symbol = Symbol.JACK
        val one = Card(suit, symbol)

        // when
        val another = Card(suit, symbol)

        // then
        assertThat(one).isEqualTo(another)
    }

    @Test
    internal fun `모든 카드`() {
        // given
        val cartesianProduct = Suit.values().flatMap { suit ->
            Symbol.values().map { symbol -> suit to symbol }
        }
        val expected = cartesianProduct.map { (suit, symbol) -> Card(suit, symbol) }

        // when
        val actual = Card.ALL

        // then
        assertThat(actual)
            .hasSize(52)
            .isEqualTo(expected)
    }
}
