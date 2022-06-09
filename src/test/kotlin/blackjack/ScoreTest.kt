package blackjack

import blackjack.domain.Score
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class ScoreTest : DescribeSpec({

    describe("SingleScore 생성자") {
        context("점수가 0보다 작다면") {
            it("IllegalArgumentException 예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    Score(-1)
                }
            }
        }
    }

    describe("isBust") {
        forAll(row(22, true), row(21, false)) { score, expected ->
            context("점수가 $score 인 경우") {
                it("$expected 를 리턴한다.") {
                    Score(score).isBust() shouldBe expected
                }
            }
        }
    }
})
