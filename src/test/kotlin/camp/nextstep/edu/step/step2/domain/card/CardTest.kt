package camp.nextstep.edu.step.step2.domain.card

import camp.nextstep.edu.step.step2.domain.card.type.CardNumbers
import camp.nextstep.edu.step.step2.domain.card.type.CardType
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

@DisplayName("카드는")
class CardTest : BehaviorSpec({

    Given("카드의 숫자와 모양이 주어지고") {
        val cardNumber = CardNumbers.ACE
        val cardType = CardType.CLUB

        When("카드를 생성하면") {
            val card = Card(
                cardNumber = cardNumber,
                cardType = cardType
            )

            Then("주어진 숫자와 모양의 카드가 생성된다") {
                card.getCardNumber() shouldBe 1
                card.getCardType() shouldBe "클로버"
            }
        }
    }

})
