package blackjack

import blackjack.domain.Deck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DeckTest {

    @Test
    fun `덱은 52장의 카드를 가진다`() {
        assertThat(Deck.cards.size).isEqualTo(52)
    }

    @Test
    fun `덱은 카드를 섞을 수 있다`() {
        val beforeShuffle = Deck.cards
        val afterShuffle = Deck.shuffle()
        assertThat(beforeShuffle).isNotEqualTo(afterShuffle)
    }
}
