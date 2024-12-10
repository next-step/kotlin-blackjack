package blackjack

import blackjack.InitialCardsTestFixtures.blackjackCards
import blackjack.InitialCardsTestFixtures.initial21Cards
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class BlackjackPushPlayerProfitCalculatorTest : BehaviorSpec({
    given("BlackjackPushPlayerProfitCalculator 는") {
        val sut = BlackjackPushPlayerProfitCalculator()
        val betAmount = Money(10000)

        `when`("플레이어가 Stay 상태일 때") {
            val playerState: State = Stay(Cards(initial21Cards))
            val result = sut.calculateProfit(playerState, betAmount)

            then("수익을 0으로 계산한다") {
                result shouldBe Money.ZERO
            }
        }

        `when`("플레이어가 Blackjack 상태일 때") {
            val playerState: State = Blackjack(Cards(blackjackCards))
            val result = sut.calculateProfit(playerState, betAmount)

            then("수익을 0으로 계산한다") {
                result shouldBe Money.ZERO
            }
        }
    }
})
