package game.blackjack

import game.blackjack.domain.Dealer
import game.blackjack.domain.Deck
import game.blackjack.domain.Participant
import game.blackjack.domain.Participants
import game.blackjack.ui.Input
import game.blackjack.ui.Output

fun main() {
    val participantNames = Input.getParticipantNames()
    val dealer = Dealer()
    val participants = Participants(participantNames.map { Participant(it) })
    val deck = Deck()

    dealer.drawCards(deck.initialDraw())
    participants.distributeInitialCards(deck)
    Output.printInitialCardsDraw(dealer, participants)

    participants.drawAdditionalCards(deck, {
        Input.isDrawAdditionalCard(it)
    }) { println(it) }
    Output.printFinalResults(participants)
}
