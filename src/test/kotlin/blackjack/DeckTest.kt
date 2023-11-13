package blackjack

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DeckTest : BehaviorSpec({
    Given("덱이 주어졌을 때") {
        val deck: Deck = FixedDeck()
        When("히트를 하면") {
            val card = deck.hit()
            Then("카드 1장을 반환한다.") {
                card shouldBe Card(CardSuit.HEART, CardNumber.TWO)
            }
        }
    }
})
