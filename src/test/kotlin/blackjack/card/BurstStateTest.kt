package blackjack.card

import blackjack.card.helper.CardsTestFactory
import domain.player.PlayerGameResult
import domain.state.Burst
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
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
    fun `버스트 상태는 수익률이 -1 이다`() {
        val cards = CardsTestFactory.makeBurstCards()

        val dealerState = Burst(cards = cards)
        val playerState = Burst(cards = cards)

        playerState.getRevenueRate(state = dealerState) shouldBe BigDecimal(-1)
    }
}
