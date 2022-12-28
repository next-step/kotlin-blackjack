package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ProfitCalculatorTest: StringSpec({
    "딜러와 플레이어가 무승부라면 베팅금액과 상관없이 플레이어의 수익은 0원이다." {
        // 12 점
        val user = User(Card(Suite.SPADE, Denomination.JACK), Card(Suite.DIAMOND, Denomination.TWO))
        val dealer = Dealer(Card(Suite.CLOVER, Denomination.EIGHT), Card(Suite.SPADE, Denomination.FOUR))

        val userResultStatus = dealer.getMatchResult(user)
        userResultStatus shouldBe ResultStatus.DRAW

        val result = ProfitCalculator().calculate(user, userResultStatus)
        result shouldBe Profit(0)
    }
})