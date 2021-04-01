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
        val allPossibleCards = listOf(
            Card(Suit.SPADES, Symbol.ACE),
            Card(Suit.SPADES, Symbol.TWO),
            Card(Suit.SPADES, Symbol.THREE),
            Card(Suit.SPADES, Symbol.FOUR),
            Card(Suit.SPADES, Symbol.FIVE),
            Card(Suit.SPADES, Symbol.SIX),
            Card(Suit.SPADES, Symbol.SEVEN),
            Card(Suit.SPADES, Symbol.EIGHT),
            Card(Suit.SPADES, Symbol.NINE),
            Card(Suit.SPADES, Symbol.TEN),
            Card(Suit.SPADES, Symbol.JACK),
            Card(Suit.SPADES, Symbol.QUEEN),
            Card(Suit.SPADES, Symbol.KING),
            Card(Suit.HEARTS, Symbol.ACE),
            Card(Suit.HEARTS, Symbol.TWO),
            Card(Suit.HEARTS, Symbol.THREE),
            Card(Suit.HEARTS, Symbol.FOUR),
            Card(Suit.HEARTS, Symbol.FIVE),
            Card(Suit.HEARTS, Symbol.SIX),
            Card(Suit.HEARTS, Symbol.SEVEN),
            Card(Suit.HEARTS, Symbol.EIGHT),
            Card(Suit.HEARTS, Symbol.NINE),
            Card(Suit.HEARTS, Symbol.TEN),
            Card(Suit.HEARTS, Symbol.JACK),
            Card(Suit.HEARTS, Symbol.QUEEN),
            Card(Suit.HEARTS, Symbol.KING),
            Card(Suit.DIAMONDS, Symbol.ACE),
            Card(Suit.DIAMONDS, Symbol.TWO),
            Card(Suit.DIAMONDS, Symbol.THREE),
            Card(Suit.DIAMONDS, Symbol.FOUR),
            Card(Suit.DIAMONDS, Symbol.FIVE),
            Card(Suit.DIAMONDS, Symbol.SIX),
            Card(Suit.DIAMONDS, Symbol.SEVEN),
            Card(Suit.DIAMONDS, Symbol.EIGHT),
            Card(Suit.DIAMONDS, Symbol.NINE),
            Card(Suit.DIAMONDS, Symbol.TEN),
            Card(Suit.DIAMONDS, Symbol.JACK),
            Card(Suit.DIAMONDS, Symbol.QUEEN),
            Card(Suit.DIAMONDS, Symbol.KING),
            Card(Suit.CLUBS, Symbol.ACE),
            Card(Suit.CLUBS, Symbol.TWO),
            Card(Suit.CLUBS, Symbol.THREE),
            Card(Suit.CLUBS, Symbol.FOUR),
            Card(Suit.CLUBS, Symbol.FIVE),
            Card(Suit.CLUBS, Symbol.SIX),
            Card(Suit.CLUBS, Symbol.SEVEN),
            Card(Suit.CLUBS, Symbol.EIGHT),
            Card(Suit.CLUBS, Symbol.NINE),
            Card(Suit.CLUBS, Symbol.TEN),
            Card(Suit.CLUBS, Symbol.JACK),
            Card(Suit.CLUBS, Symbol.QUEEN),
            Card(Suit.CLUBS, Symbol.KING)
        )

        // when
        val actual = Card.ALL

        // then
        assertThat(actual)
            .hasSize(52)
            .isEqualTo(allPossibleCards)
    }
}
