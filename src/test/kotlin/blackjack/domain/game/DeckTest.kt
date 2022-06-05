package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.util.ALL_CARDS
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DeckTest {

    @Test
    fun `덱에서 카드를 한 장 뽑을 수 있다`() {
        // given
        val deck = Deck(ALL_CARDS)

        // when
        val card = deck.draw()

        // then
        assertThat(card).isInstanceOf(Card::class.java)
        assertThat(deck.size()).isEqualTo(51)
    }

    @Test
    fun `덱에 있는 카드들을 섞을 수 있다`() {
        // given
        val deck = Deck(ALL_CARDS)

        // when
        val shuffledDeck = deck.shuffle()

        // then
        assertThat(deck).isNotEqualTo(shuffledDeck)
    }
}
