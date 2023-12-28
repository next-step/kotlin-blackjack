package game.blackjack.v2

import game.blackjack.v2.domain.Participants
import game.blackjack.v2.domain.participant.Dealer
import game.blackjack.v2.domain.participant.Player
import game.blackjack.v2.ui.InputView
import game.blackjack.v2.ui.OutputView

fun main() {
    val participantNames = InputView.getParticipantNames()
    val participants = Participants(Dealer(), participantNames.map { Player(it) })
    val dealer = participants.dealer
    val players = participants.players

    participants.distributeInitialCards()
    OutputView.printDrawInitialCardsPrompt(participants)
    OutputView.printDealerHandCards(dealer)
    OutputView.printPlayersHandCard(players)

    participants.drawPlayerAdditionalCards({
        InputView.isDrawAdditionalCard(it)
    }) { OutputView.printPlayerHandCard(it) }

    participants.drawDealerAdditionalCardsIfRequired {
        OutputView.printDealerDrawAdditionalCardPrompt()
    }

    OutputView.printDealerHandCardsAndScore(dealer)
    OutputView.printPlayersHandCardsAndScore(players)

    participants.finishGame()

    OutputView.printFinalGameResult(participants)
}
