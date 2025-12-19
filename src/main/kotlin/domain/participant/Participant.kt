package domain.participant

import domain.card.Card

abstract class Participant(
    val participantHand: ParticipantHand = ParticipantHand(),
) {
    fun receiveCard(card: Card) = participantHand.receiveCard(card)

    fun score(): Int = participantHand.calculateScore()

    fun cardSize() = participantHand.ownCards.size
}
