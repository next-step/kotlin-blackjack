package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BlackJackGameResultsTest : StringSpec({
    "딜러의 승패 결과를 계산한다." {
        val sut = BlackJackGameResults(
            value = listOf(
                BlackJackGameResult(
                    dealer = true,
                    playerName = "dealer",
                    cards = listOf(),
                    totalValue = 20
                ),
                BlackJackGameResult(
                    dealer = false,
                    playerName = "player2",
                    cards = listOf(),
                    totalValue = 19
                ),
                BlackJackGameResult(
                    dealer = false,
                    playerName = "player3",
                    cards = listOf(),
                    totalValue = 22
                ),
                BlackJackGameResult(
                    dealer = false,
                    playerName = "player4",
                    cards = listOf(),
                    totalValue = 21
                ),
            )
        )

        sut.dealerScore shouldBe DealerScore(2, 1)
    }

    "플레이어의 승패 결과를 계산한다." {
        val sut = BlackJackGameResults(
            value = listOf(
                BlackJackGameResult(
                    dealer = true,
                    playerName = "dealer",
                    cards = listOf(),
                    totalValue = 20
                ),
                BlackJackGameResult(
                    dealer = false,
                    playerName = "player2",
                    cards = listOf(),
                    totalValue = 19
                ),
                BlackJackGameResult(
                    dealer = false,
                    playerName = "player3",
                    cards = listOf(),
                    totalValue = 22
                ),
                BlackJackGameResult(
                    dealer = false,
                    playerName = "player4",
                    cards = listOf(),
                    totalValue = 21
                ),
            )
        )

        sut.playerWinningOrLose shouldBe mapOf(
            "player2" to false,
            "player3" to false,
            "player4" to true,
        )
    }
})
