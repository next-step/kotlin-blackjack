package blackjack

import io.kotest.core.spec.style.DescribeSpec
import org.junit.jupiter.api.assertThrows

class ScoreTest : DescribeSpec({

    describe("SingleScore 생성자") {
        context("점수가 0보다 작다면") {
            it("IllegalArgumentException 예외가 발생한다.") {
                assertThrows<IllegalArgumentException> {
                    SingleScore(-1)
                }
            }
        }
    }
})
