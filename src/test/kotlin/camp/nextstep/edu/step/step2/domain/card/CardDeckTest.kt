package camp.nextstep.edu.step.step2.domain.card

import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import org.junit.jupiter.api.Assertions.assertNotNull

@DisplayName("카드 덱은")
class CardDeckTest : BehaviorSpec({

    Given("카드 덱이 존재하고") {
        val cardDeck = CardDeck.DrawCard()

        When("한장을 드로우하면") {
            val card = cardDeck.draw()

            Then("카드 한장이 나온다") {
                assertNotNull(card)
            }
        }
    }

})
