package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class ProfitCalculatorTest : StringSpec({
    "딜러와 플레이어가 무승부라면 베팅금액과 상관없이 플레이어의 수익은 0원이다." {
        // 12 점
        val user = User(Card(Suite.SPADE, Denomination.JACK), Card(Suite.DIAMOND, Denomination.TWO))
        val dealer = Dealer(Card(Suite.CLOVER, Denomination.EIGHT), Card(Suite.SPADE, Denomination.FOUR))

        val userResultStatus = dealer.getMatchResult(user)
        userResultStatus shouldBe ResultStatus.DRAW

        val result = ProfitCalculator().calculate(user, userResultStatus)
        result shouldBe Profit(0)
    }

    "플레이어가 블랙잭일 때 딜러가 블랙잭이 아니라면 플레이어는 베팅금액의 2.5배를 받는다." {
        val user = User(Card(Suite.SPADE, Denomination.ACE), Card(Suite.CLOVER, Denomination.KING))
        val dealer = Dealer(Card(Suite.CLOVER, Denomination.TWO), Card(Suite.HEART, Denomination.NINE))

        val userResultStatus = dealer.getMatchResult(user)
        userResultStatus shouldBe ResultStatus.WIN

        val result = ProfitCalculator().calculate(user, userResultStatus)
        result.value shouldBe user.betAmount.value * 2.5
    }

    "플레이어가 승리하면 베팅금액의 2배를 받는다." {
        val user = User(Card(Suite.SPADE, Denomination.ACE), Card(Suite.CLOVER, Denomination.NINE))
        val dealer = Dealer(Card(Suite.CLOVER, Denomination.TWO), Card(Suite.HEART, Denomination.NINE))

        val userResultStatus = dealer.getMatchResult(user)
        userResultStatus shouldBe ResultStatus.WIN

        val result = ProfitCalculator().calculate(user, userResultStatus)
        result.value shouldBe user.betAmount.value * 2
    }
})
