package blackjack

import blackjack.domain.DealerResult
import blackjack.domain.PlayerResult
import blackjack.domain.PlayerResults
import blackjack.domain.player.Dealer
import blackjack.domain.player.GameResult
import blackjack.domain.player.Name
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerBetAmount
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class RevenueTest {
    @Test
    fun `플레이어는 게임 결과에 따라 수익을 계산할 수 있다`() {
        val betAmount = PlayerBetAmount(5000L)
        val player = Player(Name("hue"), betAmount)

        with(player.betAmount) {
            val winRevenue = this.calcRevenue(GameResult.WIN, false)
            val winRevenueWithBlackjack = this.calcRevenue(GameResult.WIN, true)
            val looseRevenue = this.calcRevenue(GameResult.LOOSE, false)
            val tieRevenue = this.calcRevenue(GameResult.TIE, false)

            winRevenue shouldBe 5000L // 승리
            winRevenueWithBlackjack shouldBe 7500L // 블랙잭 승리
            looseRevenue shouldBe -5000L // 패배
            tieRevenue shouldBe 0 // 비김
        }
    }

    @Test
    fun `플레이어가 블랙잭으로 딜러를 이기지 않으면, 딜러의 최종 수익은 플레이어들의 최종 수익의 합과 음수 관계에 있다`() {
        val dealer = Dealer()
        val playerResult1 = getPlayerResultWithCalcRevenue(Name("hue"), GameResult.WIN, false, 30000L)
        val playerResult2 = getPlayerResultWithCalcRevenue(Name("jason"), GameResult.LOOSE, false, 20000L)
        val playerResult3 = getPlayerResultWithCalcRevenue(Name("flamm"), GameResult.LOOSE, false, 5000L)
        val playerResults = PlayerResults(listOf(playerResult1, playerResult2, playerResult3))

        DealerResult.from(dealer, playerResults)

        dealer.revenue.value shouldBe -5000L
    }

    @Test
    fun `플레이어가 블랙잭으로 딜러를 이기면, 딜러의 최종 수익은 블랙잭으로 이긴 플레이어의 원금과 그 절반을 더한 값을 지불해야 한다`() {
        val dealer = Dealer()
        val playerResult1 = getPlayerResultWithCalcRevenue(Name("hue"), GameResult.WIN, true, 30000L)
        val playerResults = PlayerResults(listOf(playerResult1))

        DealerResult.from(dealer, playerResults)

        dealer.revenue.value shouldBe -45000L
    }

    private fun getPlayerResultWithCalcRevenue(
        name: Name,
        gameResult: GameResult,
        isBlackjack: Boolean,
        initBetAmount: Long,
    ): PlayerResult {
        val playerBetAmount = PlayerBetAmount(initBetAmount)
        playerBetAmount.calcRevenue(gameResult, isBlackjack)
        return PlayerResult(Player(name, playerBetAmount), gameResult)
    }
}
