package next.step.blackjack.util

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol

class CombinationUtilsTest : BehaviorSpec({
    Given("CombinationUtils.possiblePoints") {
        When("List<Card> 넣으면") {
            Then("가능한 점수 셋 제공") {
                val cards = listOf(
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.ACE, CardSymbol.HEART),
                    Card.of(CardFace.ACE, CardSymbol.CLUB),
                    Card.of(CardFace.ACE, CardSymbol.HEART),
                )

                CombinationUtils.possiblePoints(cards) shouldBe setOf(4, 3 + 11, 2 + 22, 1 + 33, 44)
            }
        }
    }

})
