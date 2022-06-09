package blackjack.domain.card

import blackjack.domain.score.Score
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class DenominationTest : DescribeSpec({

    describe("constructor") {
        context("2~10 사이의 숫자가 주어지면") {
            it("숫자 카드 타입을 생성한다") {
                listOf(2, 10).forAll { NumberCard(it) shouldNotBe null }
            }
        }

        context("2~10 이외의 숫자가 주어지면") {
            it("IllegalArgumentException 예외가 발생한다") {
                listOf(-1, 0, 11).forAll {
                    shouldThrow<IllegalArgumentException> { NumberCard(it) }
                }
            }
        }
    }

    describe("equals") {
        it("카드 유형이 동일한지 비교할 수 있다") {
            assertSoftly {
                Ace() shouldBe Ace()
                King() shouldBe King()
                Queen() shouldBe Queen()
                Jack() shouldBe Jack()
                NumberCard(2) shouldBe NumberCard(2)
            }
        }
    }

    describe("score") {
        context("숫자 카드가 주어지면") {
            it("숫자 그대로의 점수를 반환한다") {
                listOf(
                    NumberCard(2) to Score(2),
                    NumberCard(10) to Score(10)
                ).forAll { (card, score) ->
                    card.score shouldBe score
                }
            }
        }

        context("Ace 카드가 주어지면") {
            it("1 또는 11 점수를 반환한다") {
                val ace = Ace()
                assertSoftly {
                    ace.score shouldBe Score(1)
                    ace.maxScore shouldBe Score(11)
                }
            }
        }

        context("King, Queen, Jack 카드가 주어지면") {
            it("10 점수를 반홚나다") {
                listOf(King(), Queen(), Jack()).forAll {
                    it.score shouldBe Score(10)
                }
            }
        }
    }
})
