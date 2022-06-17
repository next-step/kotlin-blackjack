package blackjack.domain.card

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class ScoreTest : DescribeSpec({

    describe("constructor") {
        context("하나의 숫자가 주어지면") {
            it("점수를 생성한다") {
                listOf(0, 10).forAll { Score(it) shouldNotBe null }
            }
        }

        context("0 미만의 숫자가 주어지면") {
            it("IllegalArgumentException 이 발생한다") {
                shouldThrow<IllegalArgumentException> { Score(-1) }
            }
        }
    }

    describe("plus") {
        context("두 점수가 주어지면") {
            it("두 점수를 더할 수 있다") {
                assertSoftly {
                    Score(1) + Score(2) shouldBe Score(3)
                }
            }
        }
    }

    describe("compareTo") {
        context("두 점수가 주어지면") {
            it("점수를 비교할 수 있다") {
                assertSoftly {
                    (Score(1) < Score(5)) shouldBe true
                    (Score(1) == Score(1)) shouldBe true
                    (Score(1) > Score(0)) shouldBe true
                }
            }
        }
    }
})
