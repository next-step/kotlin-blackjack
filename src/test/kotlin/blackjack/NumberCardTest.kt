package blackjack

import balckjack.CardPattern
import balckjack.NumberCard
import balckjack.SingleCount
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class NumberCardTest : DescribeSpec({
    describe("constructor") {
        context("카드 문양과 1~10 사이의 숫자가 주어졌을 때") {
            it("숫자 카드를 생성한다") {
                listOf(1, 10).forAll {
                    NumberCard(CardPattern.HEART, it) shouldNotBe null
                }
            }
        }

        context("카드 문양과 1~10 이외의 숫자가 주어졌을 때") {
            it("IllegalArgumentException 이 발생한다") {
                listOf(-1, 0, 11).forAll {
                    shouldThrow<IllegalArgumentException> { NumberCard(CardPattern.HEART, it) }
                }
            }
        }
    }

    describe("count") {
        it("숫자 카드의 숫자 계산은 카드 숫자를 반환한다") {
            listOf(
                NumberCard(CardPattern.HEART, 1) to 1,
                NumberCard(CardPattern.CLOVER, 10) to 10,
            ).forAll { (numberCard, expectedCount) ->
                numberCard.count() shouldBe SingleCount(expectedCount)
            }
        }
    }
})
