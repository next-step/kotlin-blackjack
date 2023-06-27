package blackjack.player

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardPattern
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class HandTest : BehaviorSpec({

    Given("에이스 카드가 포함된 카드가 주어지면") {
        val ace = Card(CardNumber.ACE, CardPattern.SPADE)
        val two = Card(CardNumber.TWO, CardPattern.HEART)
        val king = Card(CardNumber.KING, CardPattern.CLOVER)

        When("카드가 Ace, King인 경우") {
            val hand = Hand()
            hand.addCard(ace)
            hand.addCard(king)
            val totalValue = hand.getTotalValue()

            Then("총 합은 21이다.") {
                totalValue.shouldBe(21)
            }
        }

        When("카드가 Ace, Two, King 인 경우") {
            val hand = Hand()
            hand.addCard(ace)
            hand.addCard(king)
            hand.addCard(two)
            val totalValue = hand.getTotalValue()

            Then("총 합은 13이다.") {
                totalValue.shouldBe(13)
            }
        }
    }
})
