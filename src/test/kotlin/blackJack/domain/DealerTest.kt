package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun is_over_16() {
        val dealer = Dealer()
        dealer.addCard(Card(Shape.SPADE, Denomination.TEN))
        dealer.addCard(Card(Shape.SPADE, Denomination.SEVEN))

        assertThat(dealer.isOver16()).isTrue()
    }

    @Test
    fun give_card() {
        val player = Player("test")
        val dealer = Dealer()

        dealer.giveCard(player)

        assertThat(player.hands).hasSize(1)
    }
}
