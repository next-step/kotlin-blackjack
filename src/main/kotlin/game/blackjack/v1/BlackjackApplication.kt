package game.blackjack.v1

import game.blackjack.v1.domain.Dealer
import game.blackjack.v1.domain.Deck
import game.blackjack.v1.domain.Participant
import game.blackjack.v1.domain.Participants
import game.blackjack.v1.ui.Input
import game.blackjack.v1.ui.Output

fun main() {
    val participantNames = Input.getParticipantNames()
    val dealer = Dealer()
    val participants = Participants(dealer, participantNames.map { Participant(it) })
    val deck = Deck()

    participants.distributeInitialCards(deck)
    Output.printInitialCardsDraw(dealer, participants)

    participants.drawAdditionalCards(deck, {
        Input.isDrawAdditionalCard(it)
    }) { println(it) }

    dealer.drawCardIfRequired(deck) {
        Output.printDealerDrawAdditionalCard()
    }
    Output.printFinalResults(dealer, participants)
}
