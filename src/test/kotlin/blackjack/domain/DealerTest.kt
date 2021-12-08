package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `make up Game Test`() {
        val dealer = Dealer.makeUpGame()

        assertThat(dealer.cards.size).isEqualTo(52)
    }

    @Test
    fun `처음 플레이어에게 카드 나눠주기`() {
        val player = Player(name = "me")
        val dealer = Dealer.makeUpGame()

        dealer.giveTwoCardsTo(player)

        assertThat(player.cards.size).isEqualTo(2)
    }

    @Test
    fun `플레이어에게 카드 한장 더 주기`() {
        val player = Player(name = "me")
        val dealer = Dealer.makeUpGame()

        dealer.giveTwoCardsTo(player)
        dealer.giveOneMoreCardTo(player)

        assertThat(player.cards.size).isEqualTo(3)
    }
}
