package blackjack

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNot
import io.kotest.matchers.shouldNotBe

class CardKoTest : DescribeSpec({

    describe("생성자") {
        context("문양와 올바른 문자가 들어오면") {
            it("카드를 리턴한다") {
                listOf(
                    Symbol.Club to 4,
                    Symbol.Heart to 2,
                    Symbol.Spade to 10
                ).forAll { (symbol, number) ->
                    NumberCard(symbol, number) shouldNotBe null
                }

                listOf(
                    Symbol.Club to 'A',
                    Symbol.Diamond to 'K',
                    Symbol.Spade to 'J'
                ).forAll { (symbol, number) ->
                    AlphabetCard(symbol, number) shouldNotBe null
                }
            }
        }
    }

    describe("score 함수"){
        context("NumberCard 인 경우") {
            it("숫자를 점수로 리턴한다") {
                (2..10).forEach {
                    NumberCard(Symbol.Heart, it).score() shouldBe it
                }
            }
        }
    }
})
