package blackjack

import blackjack.domain.AlphabetCard
import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.NumberCard
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
                val cardDeck = CardDeck(2, 3, 4)
                player.draw(cardDeck)
                player.draw(cardDeck)
                player.cardCount shouldBe 2
            }
        }
    }

    describe("canDraw") {
        context("카드의 점수 합이 21이하이면 ") {
            it("true 를 리턴한다.") {
                val player = Player("name", PlayerCards(Card(4), Card('J')))
                player.canDraw() shouldBe true
            }
        }
        context("카드의 점수 합이 21이상이면 ") {
            it("false 를 리턴한다.") {
                val player = Player("name", PlayerCards(Card('J'), Card('Q'), Card('K')))
                player.canDraw() shouldBe false
            }
        }
    }

    describe("currentCard") {
        context("카드가 다이아 2,3,4 을 가지고 있다면") {
            it("다이아 2,3,4 을 리턴한다.") {
                val player = Player("name", PlayerCards(Card(2), Card(3), Card(4)))
                player.currentCards() shouldContainAll listOf(Card(2), Card(3), Card(4))
            }
        }
    }

    describe("isBust") {
        context("카드 점수의 합이 21초과이면") {
            it("true 를 리턴한다.") {
                val player = Player("name", PlayerCards(Card(4), Card(9), Card('J')))
                player.isBust() shouldBe true
            }
        }
        context("카드의 점수 합이 21이하이면 ") {
            it("false 를 리턴한다.") {
                val player = Player("name", PlayerCards(Card('Q'), Card('K')))
                player.isBust() shouldBe false
            }
        }
    }
})

private fun PlayerCards(vararg cards: Card) = blackjack.domain.PlayerCards(cards.toList())
private fun Card(number: Int) = NumberCard(Symbol.Diamond, number)
private fun Card(char: Char) = AlphabetCard(Symbol.Diamond, char)
private fun CardDeck(vararg numbers: Int) = CardDeck(numbers.map { Card((it)) })
