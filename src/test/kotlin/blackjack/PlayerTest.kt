package blackjack

import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.CardNumber
import blackjack.domain.Player
import blackjack.domain.Symbol
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe

class PlayerTest : DescribeSpec({

    describe("draw") {
        context("카드 덱이 비어있지 않다면") {
            it("호출 횟수 만큼 카드를 가져간다.") {
                val player = Player("name")
                val cardDeck = CardDeck(CardNumber.Num2, CardNumber.Num3, CardNumber.Num4)
                player.draw(cardDeck)
                player.draw(cardDeck)
                player.cardCount shouldBe 2
            }
        }
    }

    describe("canDraw") {
        context("카드의 점수 합이 21이하이면 ") {
            it("true 를 리턴한다.") {
                val player = Player("name", PlayerCards(CardNumber.Num4, CardNumber.Jack))
                player.canDraw() shouldBe true
            }
        }
        context("카드의 점수 합이 21이상이면 ") {
            it("false 를 리턴한다.") {
                val player = Player("name", PlayerCards(CardNumber.Jack, CardNumber.Queen, CardNumber.King))
                player.canDraw() shouldBe false
            }
        }
    }

    describe("currentCard") {
        context("카드가 다이아 2,3 을 가지고 있다면") {
            it("다이아 2,3 을 리턴한다.") {
                val player = Player("name", PlayerCards(CardNumber.Num2, CardNumber.Num3))
                player.currentCards() shouldContainAll listOf(CardNumber.Num2, CardNumber.Num3).map { Card(it) }
            }
        }
    }

    describe("isBust") {
        context("카드 점수의 합이 21초과이면") {
            it("true 를 리턴한다.") {
                val player = Player("name", PlayerCards(CardNumber.Num4, CardNumber.Num9, CardNumber.Jack))
                player.isBust() shouldBe true
            }
        }
        context("카드의 점수 합이 21이하이면 ") {
            it("false 를 리턴한다.") {
                val player = Player("name", PlayerCards(CardNumber.Queen, CardNumber.King))
                player.isBust() shouldBe false
            }
        }
    }

    describe("isBlackJack") {
        context("카드가 블랙잭인 경우") {
            it("true 를 리턴한다.") {
                val player = Player("name", PlayerCards(CardNumber.Ace, CardNumber.Jack))
                player.isBlackJack() shouldBe true
            }
        }
        context("카드가 블랙잭이 아닌 경우") {
            it("false 를 리턴한다.") {
                val player = Player("name", PlayerCards(CardNumber.Num2,CardNumber.Num9, CardNumber.Jack))
                player.isBlackJack() shouldBe false
            }
        }
    }
})

private fun PlayerCards(vararg cards: CardNumber) = blackjack.domain.PlayerCards(cards.toList().map { Card(it) })
private fun Card(number: CardNumber) = Card(Symbol.Diamond, number)
private fun CardDeck(vararg numbers: CardNumber) = CardDeck(numbers.map { Card((it)) })
