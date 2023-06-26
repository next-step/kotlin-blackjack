package blackjack.card

import blackjack.card.helper.CardsTestFactory
import domain.player.PlayerGameResult
import domain.state.Burst
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BurstStateTest {

    @Test
    fun `버스트 상태에서는 무조건 진다`() {
        val cards = CardsTestFactory.makeBurstCards()
        val dealer = Burst(cards)

        val burstState = Burst(cards = cards)

        burstState.getPlayerGameResult(dealer) shouldBe PlayerGameResult.LOSE
    }

    @Test
    fun `버스트 상태에서는 무조건 잃는다`() {
        val cards = CardsTestFactory.makeBurstCards()
        val betAmount = 1000

        val dealerState = Burst(cards = cards, betAmount = betAmount)
        val playerState = Burst(cards = cards, betAmount = betAmount)

        playerState.getRevenue(state = dealerState) shouldBe -1000
    }
}
