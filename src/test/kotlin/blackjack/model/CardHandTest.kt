package blackjack.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class CardHandTest : BehaviorSpec({
    Given("번호가 K와 1을 가지고 있는 카드 핸드") {
        val cardDeck = CardDeck()
        val cardDealer = CardDealer(cardDeck)
        val cardHand = CardHand(
            listOf(
                Card(CardValue.from("K"), CardSuit.SPADES),
                Card(CardValue.from("2"), CardSuit.SPADES)
            )
        )
        When("점수를 계산") {
            val score = cardHand.getTotalScore()
            Then("12") {
                score shouldBe 12
            }
        }
        When("카드를 추가할 때") {
            cardHand.addCard(cardDealer.getCard())
            Then("카드가 추가된다") {
                cardDeck.cards.size shouldBe 51
            }
        }
    }
})
