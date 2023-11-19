package blackjack.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class CardDeckTest : BehaviorSpec({
    Given("카드 덱") {
        val deck = CardDeck(emptyList());
        When("카드를 추가할 때") {
            deck.addCard(CardDealer.getCard())
            Then("카드가 추가된다") {
                deck.cards.size shouldBe 1
            }
        }
    }
})
