package blackjack.domain.player

import blackjack.domain.Deck
import blackjack.domain.Score
import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class PlayerCardsTest : BehaviorSpec({

    Given("카드가 주어졌을 때 - 1") {
        val playerCards = PlayerCards()
        val deck = DeckHelper.createMockDeck()
        val card = deck.draw()

        When("카드를 한 장 추가하면") {
            playerCards.add(card)

            Then("카드가 추가된다.") {
                playerCards.values.size shouldBe 1
                playerCards.values shouldBe listOf(card)
            }
        }
    }

    Given("카드가 주어졌을 때 - 2") {
        val playerCards = PlayerCards()
        val deck = DeckHelper.createMockDeck()
        addCards(5, playerCards, deck)

        When("카드의 점수를 계산하면") {
            val score = playerCards.calculateScore()

            Then("카드의 총 점수가 반환된다.") {
                score shouldBe Score(15)
            }
        }
    }

    Given("카드가 주어졌을 때 - 3") {
        val playerCards = PlayerCards()
        val deck = DeckHelper.createMockDeck()
        addCards(10, playerCards, deck)

        When("버스트가 되었다면") {
            val result = playerCards.isBust()

            Then("true를 반환한다.") {
                result.shouldBeTrue()
            }
        }
    }

    Given("카드가 주어졌을 때 - 4") {
        val playerCards = PlayerCards()
        val deck = DeckHelper.createMockDeck()
        addCards(2, playerCards, deck)

        When("버스트가 되지 않았다면") {
            val result = playerCards.isBust()

            Then("false를 반환한다.") {
                result.shouldBeFalse()
            }
        }
    }

    Given("카드에 에이스가 존재하면") {
        val playerCards = PlayerCards()
        val deck = DeckHelper.createMockDeck()
        addCards(2, playerCards, deck)

        When("점수를 계산할 때") {
            val score = playerCards.calculateScore()

            Then("에이스의 점수 규칙에 따라 블랙잭에 가까운 값을 반환한다.") {
                score shouldBe Score(13)
            }
        }
    }
})

private fun addCards(count: Int, playerCards: PlayerCards, deck: Deck) {
    repeat(count) {
        val card = deck.draw()
        playerCards.add(card)
    }
}
