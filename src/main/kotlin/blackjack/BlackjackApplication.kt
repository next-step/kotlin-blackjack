package blackjack

import blackjack.domain.BlackjackGame
import blackjack.domain.BlackjackResults
import blackjack.domain.BlackjackShoe
import blackjack.domain.Participants
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackjackApplication(
    private val inputView: InputView,
    private val outputView: OutputView,
) {

    fun run() {
        val blackjackShoe = BlackjackShoe()
        val blackjackGame = ready(blackjackShoe)
        play(blackjackGame = blackjackGame, blackjackShoe = blackjackShoe)
        finish(blackjackGame = blackjackGame)
    }

    private fun ready(blackjackShoe: BlackjackShoe): BlackjackGame {
        val participantNames: List<String> = inputView.getParticipantNames()
        val participants = Participants(participantNames = participantNames.toTypedArray())
        val blackjackGame = BlackjackGame(participants = participants)

        blackjackGame.betParticipant(inputView::getBettingMoney)
        blackjackGame.receiveFirstTurnCard(blackjackShoe)
        outputView.showReady(blackjackGame = blackjackGame)
        return blackjackGame
    }

    private fun play(blackjackGame: BlackjackGame, blackjackShoe: BlackjackShoe) {
        blackjackGame.drawCardOfPlayer(
            blackjackShoe = blackjackShoe,
            getMoreCard = inputView::getMoreCard,
            onReceivedCard = outputView::showParticipantCardList
        )

        blackjackGame.drawCardOfDealer(
            blackjackShoe = blackjackShoe,
            onReceivedCard = outputView::showDealerReceivedCard
        )
    }

    private fun finish(blackjackGame: BlackjackGame) {
        val dealer = blackjackGame.dealer
        val participants = blackjackGame.participants
        outputView.showDealerInfo(dealer = dealer)
        outputView.showParticipantsInfo(participantList = participants)
        outputView.showResult(BlackjackResults(dealer = dealer, participants = participants))
    }

}

fun main() {
    val inputView = InputView()
    val outputView = OutputView()
    val blackjackApplication = BlackjackApplication(inputView, outputView)
    blackjackApplication.run()
}
