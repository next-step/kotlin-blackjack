package blackjack.player

import blackjack.playingcard.Card
import blackjack.playingcard.Cards
import blackjack.playingcard.Suit
import blackjack.playingcard.Symbol
import blackjack.playingcard.Value
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class HandTest {
    @Test
    fun `손패는 카드리스트를 가진다`() {
        assertDoesNotThrow { Hand(cards = Cards()) }
    }

    @ParameterizedTest
    @CsvSource(
        "SPADES, ACE",
        "HEARTS, SEVEN"
    )
    fun `손패에 카드를 주어 카드를 추가할 수 있다`(suit: Suit, symbol: Symbol) {
        // given
        val givenCard = Card.of(Suit.DIAMONDS, Symbol.NINE)
        val hand = Hand(Cards(listOf(givenCard)))

        val newCard = Card.of(suit, symbol)
        val expected = listOf(givenCard, newCard)

        // when
        hand.add(newCard)
        val actual = hand.cards.toList()

        // then
        assertThat(actual).containsExactlyElementsOf(expected)
    }

    @Test
    internal fun `손패는 아무 값 없이 생성하면 비어있다`() {
        val actual = Hand().cards.toList()

        assertThat(actual).isEmpty()
    }

    @Test
    internal fun `손패가 비어있으면 합은 0이다`() {
        val hand = Hand()

        assertThat(hand.sumValues()).isEqualTo(Value(0))
    }

    @ParameterizedTest
    @MethodSource
    fun `손패 값은 카드 값의 합과 같다`(expected: Value, cards: Cards) {
        // when
        val actual = Hand(cards).sumValues()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    companion object {
        @JvmStatic
        fun `손패 값은 카드 값의 합과 같다`(): Stream<Arguments> = Stream.of(
            Arguments.of(Value(11), Cards(listOf(Card.of(Suit.SPADES, Symbol.ACE)))),
            Arguments.of(
                Value(15),
                Cards(
                    listOf(
                        Card.of(Suit.SPADES, Symbol.FIVE),
                        Card.of(Suit.SPADES, Symbol.TEN)
                    )
                )
            ),
            Arguments.of(
                Value(30),
                Cards(
                    listOf(
                        Card.of(Suit.SPADES, Symbol.KING),
                        Card.of(Suit.SPADES, Symbol.QUEEN),
                        Card.of(Suit.SPADES, Symbol.JACK)
                    )
                )
            )
        )
    }
}
