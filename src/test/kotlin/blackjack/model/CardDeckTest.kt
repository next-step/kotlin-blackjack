package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    @Test
    internal fun `하나의 덱은 52개의 카드로 구성된다`() {
        val deck = CardDeck()
        assertThat(deck.cards.size).isEqualTo(52)
    }

    @Test
    internal fun `하나의 카드를 지급한다`() {
        val deck = CardDeck()
        assertThat(deck.getSingleCard()).isNotNull
    }

    @Test
    internal fun `지급 후 덱에서 카드가 없어진다`() {
        val deck = CardDeck()
        val cards = List(10) { deck.getSingleCard() }
        assertThat(deck.cards.size).isEqualTo(42)
        assertThat(deck.cards).doesNotContainAnyElementsOf(cards)
    }

    @Test
    internal fun `덱에 카드가 없으면 뽑을 수 없다`() {
        val deck = CardDeck()
        repeat(52) { deck.getSingleCard() }
        assertThrows<NoSuchElementException> { deck.getSingleCard() }
    }
}
