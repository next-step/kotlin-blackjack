package blackjack

import blackjack.domain.Card
import blackjack.domain.CardNumber
import blackjack.domain.CardShape
import blackjack.domain.Cards
import blackjack.domain.Person
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class PersonTest : BehaviorSpec({
    Given("특정 사람이 ") {
        val person1 = Person("길상현")
        When("카드를 추가하면 ") {
            person1.addCard(Card(CardShape.CLOVER, CardNumber.QUEEN))
            Then("정상적으로 카드를 추가한다.") {
                person1.name shouldBe "길상현"
                person1.cards.cards.first().cardShape shouldBe CardShape.CLOVER
                person1.cards.cards.first().cardNumber shouldBe CardNumber.QUEEN
            }
        }

        val person2 = Person("길상현", Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN))))
        When("점수를 계산하면 ") {
            val score = person2.getScore()
            Then("정상적으로 점수를 계산한다.") {
                score shouldBe 10
            }
        }
    }
})
