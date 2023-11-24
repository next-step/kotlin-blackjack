package blackjack

import blackjack.card.Card
import blackjack.hand.Hand

interface BlackjackParticipant {
    val hand: Hand
    fun receiveCard(card: Card): BlackjackParticipant
    fun calculateBestValue(): Int
}
