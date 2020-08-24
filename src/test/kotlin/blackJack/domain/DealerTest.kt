package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun make_dealer() {
        val dealer = Dealer()

        assertThat(dealer.deck.cards).hasSize(52)
        assertThat(dealer.name).isEqualTo("딜러")
        assertThat(dealer.hands).isEmpty()
    }

    @Test
    fun shuffle_deck() {
        val dealer = Dealer()
        val cards = listOf(SPADE_JACK, SPADE_QUEEN)

        dealer.shuffleDeck(cards)

        assertThat(dealer.deck.cards).hasSize(2)
    }
}
