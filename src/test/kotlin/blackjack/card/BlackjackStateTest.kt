package blackjack.card

import blackjack.card.helper.CardsTestFactory
import domain.state.Blackjack
import domain.state.Stand
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackStateTest {

    @Test
    fun `플레이어와 딜러 둘 다 블랙잭 상태인 경우 수익은 0 이다`() {
        val blackCards = CardsTestFactory.makeBlackjackCards()
        val betAmount = 1000

        val blackjackState = Blackjack(cards = blackCards, betAmount = betAmount)

        blackjackState.getRevenue(blackjackState) shouldBe 0
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러는 블랙잭이 아니라면 수익은 1점5배 다`() {
        val blackCards = CardsTestFactory.makeBlackjackCards()
        val betAmount = 1000

        val dealerState = Stand(CardsTestFactory.makeStandCards())
        val blackjackState = Blackjack(cards = blackCards, betAmount = betAmount)

        blackjackState.getRevenue(dealerState) shouldBe 1500
    }
}
