package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun give_card() {
        val player = Player("test")
        val dealer = Dealer()

        dealer.giveCard(player)

        assertThat(player.hands).hasSize(1)
    }

    @Test
    fun dealer_can_not_get_card() {
        val dealer = Dealer()
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))

        dealer.takeCard()

        assertThat(dealer.hands).hasSize(2)
    }

    @Test
    fun dealer_can_get_card() {
        val dealer = Dealer()
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.SIX))

        dealer.takeCard()

        assertThat(dealer.getHandsSize()).isNotEqualTo(2)
    }

    @Test
    fun shuffle_deck() {
        val dealer = Dealer()
        val deck = listOf(Card(Shape.SPADE, Denomination.SIX), Card(Shape.HEART, Denomination.ACE))

        dealer.shuffleDeck(deck)

        assertThat(dealer.getDeckSize()).isEqualTo(2)
    }
}
