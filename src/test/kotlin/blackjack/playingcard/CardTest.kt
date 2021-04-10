package blackjack.playingcard

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.EnumSource

internal class CardTest {
    @ParameterizedTest
    @CsvSource(
        "SPADES, ACE",
        "HEARTS, KING"
    )
    internal fun `카드는 문양 하나와 눈 하나를 가진다`(suit: Suit, symbol: Symbol) {
        val card = Card.of(suit = suit, symbol = symbol)

        assertAll(
            { assertThat(card.suit).isEqualTo(suit) },
            { assertThat(card.symbol).isEqualTo(symbol) }
        )
    }

    @Test
    internal fun `카드는 문양과 눈이 같으면 동일하다`() {
        val cartesianProduct = Suit.values().flatMap { suit ->
            Symbol.values().map { symbol -> suit to symbol }
        }

        assertThat(cartesianProduct).allSatisfy { (suit, symbol) ->
            val one = Card.of(suit, symbol)
            val other = Card.of(suit, symbol)
            assertThat(one).isSameAs(other)
        }
    }

    @ParameterizedTest
    @EnumSource
    internal fun `카드의 값은 카드 눈의 값과 동일하다`(symbol: Symbol) {
        // given
        val sumOthers = Value(10)

        // when
        val card = Card.of(Suit.HEARTS, symbol)
        val actual = card.valueBy(Value(10))

        assertThat(actual).isEqualTo(symbol.valueBy(sumOthers))
    }

    @Test
    internal fun `모든 카드`() {
        // given
        val cartesianProduct = Suit.values().flatMap { suit ->
            Symbol.values().map { symbol -> suit to symbol }
        }
        val expected = cartesianProduct.map { (suit, symbol) -> Card.of(suit, symbol) }

        // when
        val actual = Card.ALL

        // then
        assertThat(actual)
            .hasSize(52)
            .isEqualTo(expected)
    }
}
