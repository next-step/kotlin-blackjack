package blackjack

import blackjack.domain.Dealer
import blackjack.domain.Participant
import blackjack.domain.Player
import blackjack.domain.PockerMachine
import blackjack.domain.strategy.SequentialCardPickStrategy
import blackjack.view.OutputView
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class PockerMachineTest : BehaviorSpec({
    Given("초기 세팅에서 ") {
        val dealer = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy())
        val participant = Participant("길상현")
        val pockerMachine = PockerMachine(dealer = dealer, players = listOf(participant, dealer))
        When("진행하면 ") {
            pockerMachine.initialize()
            Then("카드가 2장이다.") {
                participant.cards.cards.size shouldBe 2
            }
        }

        When("카드를 추가하면 조건만큼 ") {
            pockerMachine.addCard({ player: Player -> player.cards.cards.size <= 2 }, OutputView::printCardState)
            Then("카드를 추가한다.") {
                participant.cards.cards.size shouldBe 3
            }
        }
    }
})
