package blackjack

import blackjack.domain.PockerMachine
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.domain.card.Cards
import blackjack.domain.enums.WinOrLose
import blackjack.domain.person.Dealer
import blackjack.domain.person.Participant
import blackjack.domain.person.Player
import blackjack.domain.strategy.SequentialCardPickStrategy
import blackjack.view.OutputView
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

internal class PockerMachineTest : BehaviorSpec({
    Given("초기 세팅에서 ") {
        val dealer = Dealer(name = "딜러", cardPickStrategy = SequentialCardPickStrategy())
        val participant = Participant("길상현", 10000L)
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

        val hasCardDealer = Dealer("딜러", 0L, Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.NUM_8))), SequentialCardPickStrategy())
        val hasCardParticipant = Participant("길상현", 10000L, Cards(mutableListOf(Card(CardShape.CLOVER, CardNumber.QUEEN), Card(CardShape.CLOVER, CardNumber.NUM_9))))
        val secondPockerMachine = PockerMachine(
            dealer = hasCardDealer,
            players = listOf(hasCardParticipant, hasCardDealer)
        )
        When("결과를 조회하면 ") {
            val gameResult = secondPockerMachine.getGameResult()
            Then("결과를 가져온다.") {
                gameResult.dealerName shouldBe "딜러"
                gameResult.participantResult.first().name shouldBe "길상현"
                gameResult.participantResult.first().winOrLose shouldBe WinOrLose.WIN
            }
        }
    }
})
