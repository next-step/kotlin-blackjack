package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun total_score_over_16() {
        val dealer = Dealer()

        assertThat(dealer.overTotalScore16(17)).isTrue()
    }

    @Test
    fun give_card() {
        val player = Player("test")
        val dealer = Dealer()

        dealer.giveCard(player)

        assertThat(player.hands).hasSize(1)
    }

    @Test
    fun dealer_can_not_get_card_over_16() {
        val dealer = Dealer()

        if (!dealer.overTotalScore16(17)) {
            dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        }

        assertThat(dealer.hands).hasSize(0)
    }
}
