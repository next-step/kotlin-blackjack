package game.blackjack

import game.blackjack.domain.Deck
import game.blackjack.domain.Participant
import game.blackjack.ui.Input
import game.blackjack.ui.Output

fun main() {
    val participants = Input.getParticipantNames().map { Participant(it) }
    val deck = Deck()

    drawInitialCards(participants, deck)
    Output.printInitialCardsDraw(participants)

    drawAdditionalCards(participants, deck)

    Output.printFinalResults(participants)
}

private fun drawInitialCards(
    participants: List<Participant>,
    deck: Deck
) {
    participants.forEach { it.drawCard(deck.initialDraw()) }
}

private fun drawAdditionalCards(
    participants: List<Participant>,
    deck: Deck
) {
    participants.forEach { drawCardsForParticipant(it, deck) }
}

private fun drawCardsForParticipant(participant: Participant, deck: Deck) {
    while (participant.isNotBust() && Input.isDrawAdditionalCard(participant)) {
        drawAndShowCard(participant, deck)
    }
}

private fun drawAndShowCard(participant: Participant, deck: Deck) {
    participant.drawCard(deck.drawOnce())
    println(participant)
}
