package blackjack

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.inspectors.forAll

class CardKoTest : DescribeSpec({

    describe("생성자") {
        context("문양와 올바른 문자가 들어오면") {
            it("카드를 리턴한다") {
                listOf(
                    Symbol.Club to 4,
                    Symbol.Heart to 2,
                    Symbol.Spade to 10
                ).forAll { (symbol, number) ->
                    NumberCard(symbol, number)
                }

                listOf(
                    Symbol.Club to 'A',
                    Symbol.Diamond to 'K',
                    Symbol.Spade to 'J'
                ).forAll { (symbol, number) ->
                    AlphabetCard(symbol, number)
                }
            }
        }
    }
})
