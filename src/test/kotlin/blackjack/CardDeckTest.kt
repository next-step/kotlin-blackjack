package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.NumberCard
import blackjack.domain.Symbol
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

private fun TestCard(number: Int) = NumberCard(Symbol.Heart, number)
private fun CardDeck(vararg numbers: Int) = CardDeck(numbers.map { TestCard((it)) })

class CardDeckTest : DescribeSpec({

    describe("constructor") {
        context("중복된 카드가 존재하는 경우") {
            it("IllegalArgumentException 예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    CardDeck(2, 2, 2)
                }
            }
        }
    }

    describe("count") {
        context("덱에 3개의 카드만 있다면") {
            it("3을 리턴한다.") {
                val deck = CardDeck(2, 3, 4)
                deck.count() shouldBe 3
            }
        }
    }

    describe("new") {
        context("새로만들어진 덱은") {
            it("52장의 카드를 가진다") {
                CardDeck.new().count() shouldBe 52
            }
        }
    }
})
