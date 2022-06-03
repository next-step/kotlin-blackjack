package blackjack

import blackjack.domain.SingleScore
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec

class ScoreTest : DescribeSpec({

    describe("SingleScore 생성자") {
        context("점수가 0보다 작다면") {
            it("IllegalArgumentException 예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    SingleScore(-1)
                }
            }
        }
    }
})
