package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
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

    @Test
    fun give_card() {
        val dealer = Dealer()
        val player = Player("joohan")
        assertThat(dealer.deck.cards).hasSize(52)

        dealer.giveCard(player)

        assertThat(player.hands).hasSize(1)
        assertThat(dealer.deck.cards).hasSize(51)
    }

    @Test
    fun dealer_can_get_card_over17() {
        val dealer = Dealer()
        dealer.addCard(SPADE_QUEEN)
        dealer.addCard(SPADE_JACK)

        assertThatThrownBy {
            dealer.giveCard(dealer)
        }.isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("딜러의 카드 총합이 17을 넘겼기 때문에 카드를 더 받을수 없습니다.")
    }
}
