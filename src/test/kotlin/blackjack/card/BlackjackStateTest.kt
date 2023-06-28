package blackjack.card

import blackjack.card.helper.CardsTestFactory
import domain.state.Blackjack
import domain.state.Stand
import io.kotest.matchers.shouldBe
import java.math.BigDecimal
import org.junit.jupiter.api.Test

class BlackjackStateTest {

    @Test
    fun `플레이어와 딜러 둘 다 블랙잭 상태인 경우 수익률은 0 이다`() {
        val blackCards = CardsTestFactory.makeBlackjackCards()

        val blackjackState = Blackjack(cards = blackCards)

        blackjackState.getRevenueRate(blackjackState) shouldBe BigDecimal.ZERO
    }

    @Test
    fun `플레이어가 블랙잭이고 딜러는 블랙잭이 아니라면 수익률은 1점5배 다`() {
        val blackCards = CardsTestFactory.makeBlackjackCards()

        val dealerState = Stand(CardsTestFactory.makeStandCards())
        val blackjackState = Blackjack(cards = blackCards)

        blackjackState.getRevenueRate(dealerState) shouldBe BigDecimal(1.5)
    }
}
