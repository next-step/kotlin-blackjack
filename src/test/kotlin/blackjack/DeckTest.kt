package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class DeckTest {
    @Test
    internal fun `덱은 카드리스트로 생성된다`() {
        // given
        val cards = listOf(Card(Suit.HEARTS, Symbol.ACE))

        assertDoesNotThrow { Deck(cards) }
    }

    @ParameterizedTest
    @CsvSource(
        "HEARTS,ACE",
        "SPADES,FIVE"
    )
    internal fun `덱에서 카드를 뽑을 수 있다`(suit: Suit, symbol: Symbol) {
        // given
        val deck = Deck(listOf(Card(suit, symbol)))
        val expectedCardList = listOf(Card(suit, symbol))

        // when
        val actualCard: Card = deck.draw()

        // then
        assertThat(actualCard).isIn(expectedCardList)
    }

    @Test
    internal fun `빈 덱에서 한 장을 뽑으려 하면 예외를 발생시킨다`() {
        val deck = Deck(listOf())

        assertThatExceptionOfType(NoSuchElementException::class.java).isThrownBy { deck.draw() }
    }

    @Test
    internal fun `덱 생성 시 주어진 카드리스트에서의 카드들만 뽑을 수 있다`() {
        // given
        val deck = Deck(
            listOf(
                Card(Suit.HEARTS, Symbol.ACE),
                Card(Suit.DIAMONDS, Symbol.TWO)
            )
        )

        val expectedCards: List<Card> = listOf(
            Card(Suit.HEARTS, Symbol.ACE),
            Card(Suit.DIAMONDS, Symbol.TWO)
        )

        // when
        val actualCards: List<Card> = List(2) { deck.draw() }

        // then
        assertThat(actualCards).containsExactlyInAnyOrderElementsOf(expectedCards)
    }

    @Test
    internal fun `초기 덱은 모든 경우의 카드를 중복없이 한 장씩 가지고 있다`() {
        // given
        val expectedCards = Card.ALL

        // when
        val newDeck: Deck = Deck.newDeck()
        val actual = List(52) { newDeck.draw() }.toSet()

        // then
        assertThat(actual).containsExactlyInAnyOrderElementsOf(expectedCards)
        assertThatExceptionOfType(NoSuchElementException::class.java).isThrownBy { newDeck.draw() }
    }
}
