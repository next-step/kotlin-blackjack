package blackjack.supoort

import blackjack.card.AceCard
import blackjack.card.CardPattern
import blackjack.card.NormalCard
import blackjack.participant.BettingAmount
import blackjack.participant.Dealer
import blackjack.participant.Name
import blackjack.participant.Player
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MatchingScoreCalculatorTest {
    private val scoreCalculator: ScoreCalculator = ScoreCalculator()

    @Test
    fun `플레이어가 블랙잭 상태로 이기면 딜러에게서 배팅금액을 받는다`() {
        val dealer = Dealer(scoreCalculator)
        dealer.drawCard(
            listOf(
                NormalCard(5, CardPattern.CLOVER),
                NormalCard(10, CardPattern.CLOVER),
            )
        )

        val player = Player(Name("홍길동"), BettingAmount(1000))
        player.drawCard(
            listOf(
                AceCard(CardPattern.CLOVER),
                NormalCard(10, CardPattern.CLOVER),
            )
        )

        val dealerBettingAmount = MatchingScoreCalculator.matchingScoreForDealer(listOf(player), dealer)

        dealerBettingAmount.amount shouldBe -1500
    }
}
