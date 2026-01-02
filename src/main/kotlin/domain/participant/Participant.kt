package domain.participant

import domain.card.Card

abstract class Participant(
    val hand: ParticipantHand = ParticipantHand(),
) {
    fun receiveCard(card: Card) = hand.receiveCard(card)

    fun score(): Int = hand.calculateScore()

    fun isBust(): Boolean = score() > ParticipantHand.BLACKJACK_MAX_SCORE
}
