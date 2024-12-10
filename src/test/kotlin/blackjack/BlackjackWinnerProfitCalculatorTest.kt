package blackjack

import blackjack.InitialCardsTestFixtures.blackjackCards
import blackjack.InitialCardsTestFixtures.initial21Cards
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackjackWinnerProfitCalculatorTest : BehaviorSpec({
    given("BlackjackWinnerProfitCalculator 는") {
        val sut: BlackjackPlayerProfitCalculator = BlackjackWinnerProfitCalculator()
        val betAmount = Money(10000)

        `when`("Player 가 Blackjack 상태일 때") {
            val playerState: State = Blackjack(Cards(blackjackCards))
            val result = sut.calculateProfit(playerState, betAmount)

            then("베팅 금액의 1.5배의 수익을 반환한다") {
                result shouldBe betAmount * 1.5
            }
        }

        `when`("Player 가 Stay 상태일 때") {
            val playerState: State = Stay(Cards(initial21Cards))
            val result = sut.calculateProfit(playerState, betAmount)

            then("베팅 금액의 1배의 수익을 반환한다") {
                result shouldBe betAmount
            }
        }
    }
})
