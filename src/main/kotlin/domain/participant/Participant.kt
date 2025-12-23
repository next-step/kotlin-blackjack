package domain.participant

import domain.card.Card

abstract class Participant(
    val hand: ParticipantHand = ParticipantHand(),
) {
    fun receiveCard(card: Card) = hand.receiveCard(card)

    fun score(): Int = hand.calculateScore()

    fun isBlackjack(): Boolean = hand.calculateScore(2) == ParticipantHand.BLACKJACK_MAX_SCORE
}
