package blackjack

import blackjack.InitialCardsTestFixtures.bustCards
import blackjack.InitialCardsTestFixtures.initial21Cards
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackjackLoserProfitCalculatorTest : BehaviorSpec({
    given("BlackjackLoserProfitCalculator 는") {
        val sut: BlackjackPlayerProfitCalculator = BlackjackLoserProfitCalculator()
        val betAmount = Money(10000)

        `when`("Player 가 Stay 상태일 때") {
            val playerState: State = Stay(Cards(initial21Cards))
            val result = sut.calculateProfit(playerState, betAmount)

            then("베팅 금액의 -1배의 수익을 반환한다") {
                result shouldBe -betAmount
            }
        }

        `when`("Player 가 Bust 상태일 때") {
            val playerState: State = Bust(Cards(bustCards))
            val result = sut.calculateProfit(playerState, betAmount)

            then("베팅 금액의 -1배의 수익을 반환한다") {
                result shouldBe -betAmount
            }
        }
    }
})
