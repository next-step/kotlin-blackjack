package blackjack.domain.winning

import blackjack.domain.card.Card
import blackjack.domain.card.RandomCardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.score.PlayerScore
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

class RevenueCalculatorTest : FreeSpec({

    "calculate" - {
        "DEALER_BUST 인 경우 플레이어는 배팅 금액을 돌려받는다." {
            val playerScore = 2
            val dealerScore = 22
            val revenueCalculator = RevenueCalculator(
                createWinningStat(1000, playerScore, dealerScore)
            )

            val result = revenueCalculator.calculate()
            result.dealerRevenue shouldBe 0
            result.players[0].revenue shouldBe 1000
        }

        "PLAYER_BUST 인 경우 플레이어는 배팅 금액을 모두 잃는다." {
            val playerScore = 22
            val dealerScore = 2
            val revenueCalculator = RevenueCalculator(
                createWinningStat(1000, playerScore, dealerScore)
            )

            val result = revenueCalculator.calculate()
            result.dealerRevenue shouldBe 1000
            result.players[0].revenue shouldBe -1000
        }

        "PLAYER_BLACKJACK 인 경우 플레이어는 배팅 금액의 1.5배를 얻는다." {
            val playerScore = 21
            val dealerScore = 2
            val player = Player("test", 1000)
            player.addCard(Card(RandomCardDeck.DIAMOND, RandomCardDeck.ACE),)
            player.addCard(Card(RandomCardDeck.DIAMOND, RandomCardDeck.JACK))
            val revenueCalculator = RevenueCalculator(
                WinningStat(
                    listOf(PlayerScore(player, playerScore)),
                    PlayerScore(Dealer(), dealerScore)
                )
            )

            val result = revenueCalculator.calculate()
            result.dealerRevenue shouldBe -(1000 * 1.5)
            result.players[0].revenue shouldBe (1000 * 1.5)
        }

        "PLAYER_WIN 인 경우 플레이어는 배팅 금액을 받는다." {
            val playerScore = 20
            val dealerScore = 2
            val revenueCalculator = RevenueCalculator(
                createWinningStat(1000, playerScore, dealerScore)
            )

            val result = revenueCalculator.calculate()
            result.dealerRevenue shouldBe -1000
            result.players[0].revenue shouldBe 1000
        }

        "PLAYER_LOOSE 인 경우 플레이어는 배팅 금액을 잃는다." {
            val playerScore = 2
            val dealerScore = 20
            val revenueCalculator = RevenueCalculator(
                createWinningStat(1000, playerScore, dealerScore)
            )

            val result = revenueCalculator.calculate()
            result.dealerRevenue shouldBe 1000
            result.players[0].revenue shouldBe -1000
        }

        "PLYAER_TIE 인 경우 플레이어는 배팅 금액을 돌려받는다." {
            val playerScore = 21
            val dealerScore = 21
            val revenueCalculator = RevenueCalculator(
                createWinningStat(1000, playerScore, dealerScore)
            )

            val result = revenueCalculator.calculate()
            result.dealerRevenue shouldBe 0
            result.players[0].revenue shouldBe 1000
        }
    }
})

fun createWinningStat(
    bettingAmount: Int,
    playerScore: Int,
    dealerScore: Int
): WinningStat {
    val player = Player("test", bettingAmount)
    return WinningStat(
        listOf(PlayerScore(player, playerScore)),
        PlayerScore(Dealer(), dealerScore)
    )
}
