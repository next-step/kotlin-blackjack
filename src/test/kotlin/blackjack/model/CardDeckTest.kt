package blackjack.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardDeckTest {
    @Test
    internal fun `하나의 덱은 52개의 카드로 구성된다`() {
        // given, when
        val deck = CardDeck.defaultDeck()

        // then
        assertThat(deck.size).isEqualTo(52)
    }

    @Test
    internal fun `하나의 카드를 지급한다`() {
        // given, when
        val deck = CardDeck.defaultDeck()

        // then
        assertThat(deck.getCard()).isNotNull
    }

    @Test
    internal fun `지급 후 덱에서 카드가 없어진다`() {
        // given
        val deck = CardDeck.defaultDeck()

        // when
        val cards = List(10) { deck.getCard() }

        // then
        assertThat(deck.size).isEqualTo(42)
        assertThat(deck).doesNotContainAnyElementsOf(cards)
    }

    @Test
    internal fun `덱에 카드가 없으면 뽑을 수 없다`() {
        // given
        val deck = CardDeck.defaultDeck()

        // when
        repeat(52) { deck.getCard() }

        // then
        assertThrows<NoSuchElementException> { deck.getCard() }
    }
}
