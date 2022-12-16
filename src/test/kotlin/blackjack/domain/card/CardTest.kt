package blackjack.domain.card

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class CardTest : BehaviorSpec({
    Given("카드가 주어진다면 ") {
        val cloverAceCard = Card(
            cardShape = CardShape.CLOVER,
            cardNumber = CardNumber.ACE
        )
        When("카드의 이름을 ") {
            Then("출력할 수 있다.") {
                cloverAceCard.toString() shouldBe "A클로버"
            }
        }

        When("점수를 ") {
            Then("계산할 수 있다. [ACE가 1로 활용될 경우]") {
                cloverAceCard.calculate(20) shouldBe 21
            }

            Then("계산할 수 있다. [ACE가 11로 활용될 경우]") {
                cloverAceCard.calculate(0) shouldBe 11
            }
        }
    }
})
