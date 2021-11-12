package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `덱의 카드는 총 52장이다`() {
        val deck = Deck()

        assertThat(deck.cards).hasSize(52)
    }

    @Test
    fun `카드를 뽑으면 전체 갯수가 줄어든다`() {
        val deck = Deck()

        deck.drawCard()

        assertThat(deck.cards).hasSize(51)
    }
}
