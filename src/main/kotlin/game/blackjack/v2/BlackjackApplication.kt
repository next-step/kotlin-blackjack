package game.blackjack.v2

import game.blackjack.v2.domain.Participants
import game.blackjack.v2.domain.participant.Dealer
import game.blackjack.v2.domain.participant.Player
import game.blackjack.v2.ui.InputView
import game.blackjack.v2.ui.OutputView

fun main() {
    val participantNames = InputView.getParticipantNames()
    val participants = Participants(Dealer(), participantNames.map { Player(it) })

    participants.distributeInitialCards()
    OutputView.printDrawInitialCardsPrompt(participants)
    OutputView.printDealerHandCards(participants.dealer)
    OutputView.printPlayersHandCard(participants.players)

    participants.drawPlayerAdditionalCards({
        InputView.isDrawAdditionalCard(it)
    }) { OutputView.printPlayerHandCard(it) }

    participants.drawDealerAdditionalCardsIfRequired {
        OutputView.printDealerDrawAdditionalCardPrompt()
    }

    OutputView.printDealerHandCardsAndScore(participants.dealer)
    OutputView.printPlayersHandCardsAndScore(participants.players)
}
