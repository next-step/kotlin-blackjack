package blackjack.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class CardDealerTest : BehaviorSpec({
    Given("카드 딜러") {
        When("카드 딜러가 카드를 2장 나눠줄때") {
            val cards = CardDealer.getCards(2);
            Then("카드를 2장 나눠준다") {
                cards.size shouldBe 2
            }
        }
    }
})
