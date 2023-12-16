package game.blackjack

import game.blackjack.domain.Deck
import game.blackjack.domain.Participant
import game.blackjack.domain.Participants
import game.blackjack.ui.Input
import game.blackjack.ui.Output

fun main() {
    val participantNames = Input.getParticipantNames()
    val participants = Participants(participantNames.map { Participant(it) })
    val deck = Deck()

    participants.distributeInitialCards(deck)
    Output.printInitialCardsDraw(participants)

    participants.drawAdditionalCards(deck, {
        Input.isDrawAdditionalCard(it)
    }) { println(it) }
    Output.printFinalResults(participants)
}
