package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class BettingAmountTest : StringSpec({
    "BettingAmount의 win 메서드는 금액 그대로를 리턴한다."{
        //given
        val bettingAmount = BettingAmount(1000)

        //when
        val amount = bettingAmount.win()

        //then
        amount shouldBe 1000
    }

    "BettingAmount의 lose 메서드는 금액에 -1을 곱한 값을 리턴한다."{
        //given
        val bettingAmount = BettingAmount(1000)

        //when
        val amount = bettingAmount.lose()

        //then
        amount shouldBe -1000
    }

    "BettingAmount의 blackJack 메서드는 금액에 1.5를 곱한 값을 리턴한다."{
        //given
        val bettingAmount = BettingAmount(1000)

        //when
        val amount = bettingAmount.blackJack()

        //then
        amount shouldBe 1500
    }

    "BettingAmount의 tie 메서드는 0을 리턴한다."{
        //given
        val bettingAmount = BettingAmount(1000)

        //when
        val amount = bettingAmount.tie()

        //then
        amount shouldBe 0
    }
})
