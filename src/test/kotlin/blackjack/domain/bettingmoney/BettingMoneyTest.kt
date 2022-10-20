package blackjack.domain.bettingmoney

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec

class BettingMoneyTest : StringSpec({
    "배팅 금액이 음수이면 예외를 발생시킨다." {
        // given
        val negativeMoney = -1_000L

        // when // then
        shouldThrowExactly<IllegalArgumentException> { BettingMoney(negativeMoney) }
    }
})
