package blackjack.domain.card

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class CardDeckTest : BehaviorSpec({
    Given("덱을 ") {
        val given = CardDeck()
        When("생성하면") {
            Then("52개가 있다.") {
                given.cards.cards.size shouldBe 52
            }
        }
    }
})
