package blackjack

import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.CardNumber
import blackjack.domain.Player
import blackjack.domain.Symbol
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
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
                player.currentCards().size shouldBe 2
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
        context("카드의 점수 합이 22를 넘으면 ") {
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
        context("카드 점수의 합이 22를 넘으면") {
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
        forAll(
            row(Player("name", PlayerCards(CardNumber.Num2, CardNumber.Num9, CardNumber.Jack))),
            row(Player("name", PlayerCards(CardNumber.Num10, CardNumber.Jack)))
        ) { player ->
            context("카드가 블랙잭이 아닌 경우") {
                it("false 를 리턴한다.") {
                    player.isBlackJack() shouldBe false
                }
            }
        }
    }

    describe("openedCards") {
        context("가지고 있는 카드가 2장인 경우") {
            it("2장을 전부 오픈 한다.") {
                val player = Player("", PlayerCards(CardNumber.Num6, CardNumber.Num10))
                player.openedCards().size shouldBe 2
            }
        }
    }
})

private fun PlayerCards(vararg cards: CardNumber) = blackjack.domain.PlayerCards(cards.toList().map { Card(it) })
private fun Card(number: CardNumber) = Card(Symbol.Diamond, number)
private fun CardDeck(vararg numbers: CardNumber) = CardDeck(numbers.map { Card((it)) })
