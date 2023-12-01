package blackjack.domain

import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.helper.DeckHelper
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class DeckTest : BehaviorSpec({

    Given("덱이 주어져 있을 때") {
        val deck = DeckHelper.createMockDeck()

        When("한 장 뽑으면") {
            val card = deck.draw()

            Then("카드가 반환된다.") {
                card.number shouldBe CardNumber.ACE
                card.pattern shouldBe CardPattern.HEART
            }
        }
    }
})
