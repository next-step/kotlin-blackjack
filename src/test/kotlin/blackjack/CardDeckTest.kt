package blackjack

import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.CardNumber
import blackjack.domain.Symbol
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

private fun TestCard(number: CardNumber) = Card(Symbol.Heart, number)
private fun CardDeck(vararg numbers: CardNumber) = CardDeck(numbers.map { TestCard((it)) })

class CardDeckTest : DescribeSpec({

    describe("constructor") {
        context("중복된 카드가 존재하는 경우") {
            it("IllegalArgumentException 예외가 발생한다.") {
                shouldThrow<IllegalArgumentException> {
                    CardDeck(CardNumber.Num2, CardNumber.Num2, CardNumber.Num2)
                }
            }
        }
    }

    describe("count") {
        context("덱에 3개의 카드만 있다면") {
            it("3을 리턴한다.") {
                val deck = CardDeck(CardNumber.Num2, CardNumber.Num3, CardNumber.Num4)
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

    describe("pop") {
        context("가장 위에 있는 카드가 하트 A 라면") {
            it("하트 A 를 리턴하고 덱에서 제거한다.") {
                val deck = CardDeck(listOf(TestCard(CardNumber.Ace)))
                deck.pop() shouldBe TestCard(CardNumber.Ace)
                deck.count() shouldBe 0
            }
        }
    }
})
