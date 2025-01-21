package blackjack

import blackjack.domain.BlackjackResults
import blackjack.domain.BlackjackShoe
import blackjack.domain.Dealer
import blackjack.domain.Participants
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackApplication(
    private val inputView: InputView,
    private val outputView: OutputView,
) {

    fun run() {
        val blackjackShoe = BlackjackShoe()
        val (dealer, participantList) = ready(blackjackShoe)
        play(dealer = dealer, participants = participantList, blackjackShoe = blackjackShoe)
        finish(dealer = dealer, participants = participantList)
    }

    private fun ready(blackjackShoe: BlackjackShoe): Pair<Dealer, Participants> {
        val participantNames: List<String> = inputView.getParticipantNames()
        val participants = Participants(participantNames = participantNames.toTypedArray())
        participants.forEach { participant ->
            participant.bettingMoney = inputView.getBettingMoney(participant)
        }

        val dealer = Dealer()
        dealer.setupCard(blackjackShoe = blackjackShoe)
        participants.setupCard(blackjackShoe = blackjackShoe)

        outputView.showReady(dealer = dealer, participants = participants)
        return dealer to participants
    }

    private fun play(dealer: Dealer, participants: Participants, blackjackShoe: BlackjackShoe) {
        participants.forEach { participant ->
            while (participant.canReceiveCard && inputView.getMoreCard(participant)) {
                val card = blackjackShoe.draw()
                participant.receiveCard(card)
                outputView.showParticipantCardList(participant)
            }
        }

        while (dealer.canReceiveCard) {
            val card = blackjackShoe.draw()
            dealer.receiveCard(card)
            outputView.showDealerReceivedCard()
        }
    }

    private fun finish(dealer: Dealer, participants: Participants) {
        outputView.showDealerInfo(dealer = dealer)
        outputView.showParticipantsInfo(participants)
        outputView.showResult(BlackjackResults(dealer = dealer, participants = participants))
    }

}

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val blackjackApplication = BlackjackApplication(inputView, outputView)
    blackjackApplication.run()
}
