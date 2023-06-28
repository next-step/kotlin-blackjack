package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class GameResultTest : FunSpec({

    context("수익을 계산한다") {
        data class GameResultProfit(
            val gameResult: GameResult,
            val profitRate: BigDecimal,
            val expected: BigDecimal
        )
        val betAmount = BigDecimal(1000)
        withData(
            nameFn = { "${it.gameResult} profitRate=${it.profitRate} betAmount=$betAmount : profit=${it.expected}" },
            GameResultProfit(GameResult.WIN, BigDecimal(1), betAmount),
            GameResultProfit(GameResult.WIN, BigDecimal(1.5), 1500.0.toBigDecimal()),
            GameResultProfit(GameResult.WIN, BigDecimal(-1), -betAmount),
            GameResultProfit(GameResult.TIE, BigDecimal(1), BigDecimal.ZERO),
            GameResultProfit(GameResult.TIE, BigDecimal(1.5), BigDecimal.ZERO),
            GameResultProfit(GameResult.TIE, BigDecimal(-1), BigDecimal.ZERO),
            GameResultProfit(GameResult.LOSE, BigDecimal(1), -betAmount),
            GameResultProfit(GameResult.LOSE, BigDecimal(1.5), -betAmount),
            GameResultProfit(GameResult.LOSE, BigDecimal(-1), -betAmount),
        ) { (gameResult, profitRate, expected) ->
            gameResult.calculateProfit(betAmount, profitRate) shouldBe Profit(expected)
        }
    }
})
