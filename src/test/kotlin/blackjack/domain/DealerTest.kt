package blackjack.domain

import org.junit.jupiter.api.Test
import io.kotest.matchers.shouldBe
import blackjack.card.CardPattern
import blackjack.card.NormalCard
import blackjack.supoort.ScoreCalculator
import blackjack.participant.Dealer

class DealerTest {
    private val scoreCalculator: ScoreCalculator = ScoreCalculator()

    @Test
    fun `딜러는 점수가 16점이하면 카드를 뽑는다`() {
        val dealer = Dealer(scoreCalculator)
        dealer.drawCard(
            listOf(
                NormalCard(9, CardPattern.CLOVER),
                NormalCard(3, CardPattern.CLOVER),
            )
        )

        dealer.shouldDraw() shouldBe true
    }

    @Test
    fun `딜러는 점수가 17점이상이면 카드를 뽑을 수 없다`() {
        val dealer = Dealer(scoreCalculator)
        dealer.drawCard(
            listOf(
                NormalCard(9, CardPattern.CLOVER),
                NormalCard(10, CardPattern.CLOVER),
            )
        )

        dealer.shouldDraw() shouldBe false
    }
}
