package blackjack

import blackjack.domain.BetMoney
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec

class BetMoneyTest : DescribeSpec({

    describe("SingleScore 생성자") {
        context("금액이 0보다 작다면") {
            it("IllegalArgumentException 예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    BetMoney(-1)
                }
            }
        }
    }
})
