package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class BlackjackPlayerProfitCalculatorHandlerTest : BehaviorSpec({
    given("BlackjackPlayerProfitCalculatorHandler 는") {
        val sut = BlackjackPlayerProfitCalculatorHandler()

        `when`("PlayerResult 가 WIN 이면") {
            val playerResult = PlayerResult.WIN
            val result: BlackjackPlayerProfitCalculator = sut.findCalculator(playerResult)

            then("BlackjackWinnerProfitCalculator 를 반환한다") {
                result.shouldBeInstanceOf<BlackjackWinnerProfitCalculator>()
            }
        }

        `when`("PlayerResult 가 LOSS 이면") {
            val playerResult = PlayerResult.LOSS
            val result: BlackjackPlayerProfitCalculator = sut.findCalculator(playerResult)

            then("BlackjackLoserProfitCalculator 를 반환한다") {
                result.shouldBeInstanceOf<BlackjackLoserProfitCalculator>()
            }
        }

        `when`("PlayerResult 가 PUSH 이면") {
            val playerResult = PlayerResult.PUSH
            val result: BlackjackPlayerProfitCalculator = sut.findCalculator(playerResult)

            then("BlackjackPushPlayerProfitCalculator 를 반환한다") {
                result.shouldBeInstanceOf<BlackjackPushPlayerProfitCalculator>()
            }
        }
    }
})
