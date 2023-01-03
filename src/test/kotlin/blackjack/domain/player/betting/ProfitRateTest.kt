package blackjack.domain.player.betting

import io.kotest.core.Tuple3
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class ProfitRateTest : FunSpec({

    context("ProfitRate::getProfit 메서드가 정상 작동한다.") {

        withData(
            nameFn = { "${it.a.amount} * ${it.b} = ${it.c.amount}" },
            listOf(
                500.0,
                750.0,
                1000.0,
                1500.0,
                2000.0,
                2500.0,
                3000.0,
            ).flatMap { givenBettingAmount ->
                listOf(
                    Tuple3(Betting(givenBettingAmount), ProfitRate.BLACKJACK_WIN_PROFIT_RATE, Profit(givenBettingAmount * 1.5)),
                    Tuple3(Betting(givenBettingAmount), ProfitRate.BLACKJACK_DRAW_PROFIT_RATE, Profit(givenBettingAmount * 1.0)),
                    Tuple3(Betting(givenBettingAmount), ProfitRate.WIN_PROFIT_RATE, Profit(givenBettingAmount * 1.0)),
                    Tuple3(Betting(givenBettingAmount), ProfitRate.DRAW_PROFIT_RATE, Profit(givenBettingAmount * 1.0)),
                    Tuple3(Betting(givenBettingAmount), ProfitRate.LOSE_PROFIT_RATE, Profit(givenBettingAmount * -1.0)),
                    Tuple3(Betting(givenBettingAmount), ProfitRate.BUST_PROFIT_RATE, Profit(givenBettingAmount * -1.0))
                )
            }
        ) { (givenBetting, profitRate, expectedProfit) ->
            profitRate.getProfit(givenBetting) shouldBe expectedProfit
            profitRate * givenBetting shouldBe expectedProfit
            givenBetting * profitRate shouldBe expectedProfit
        }
    }
})
