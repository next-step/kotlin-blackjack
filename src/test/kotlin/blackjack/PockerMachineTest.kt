package blackjack

import blackjack.domain.Person
import blackjack.domain.PockerMachine
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class PockerMachineTest : BehaviorSpec({
    Given("초기 세팅에서 ") {
        val pockerMachine = PockerMachine()
        val person = Person("길상현")
        When("진행하면 ") {
            pockerMachine.initialize(person)
            Then("카드가 2장이다.") {
                person.cards.cards.size shouldBe 2
            }
        }

        When("카드를 추가하면 조건만큼 ") {
            pockerMachine.addCard({ person: Person -> person.cards.cards.size <= 2 }, person)
            Then("카드를 추가한다.") {
                person.cards.cards.size shouldBe 3
            }
        }
    }
})
