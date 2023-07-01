package blackjack.domain.game

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class DealerProfitResultTest : StringSpec({

    "딜러의 수익은 플레이어들 총 수익의 -1배이다" {
        val playerProfitResults = listOf(
            PlayerProfitResult("test1", 1000.0),
            PlayerProfitResult("test2", 2000.0),
            PlayerProfitResult("test3", -4000.0),
        )
        val playerTotalProfit = playerProfitResults.sumOf { it.profit }
        DealerProfitResult.create(playerProfitResults).profit shouldBe playerTotalProfit * -1
    }
})
