package blackjack

import blackjack.card.Card

interface BlackjackParticipant {
    fun receiveCard(card: Card): BlackjackParticipant
    fun receiveCard(cards: List<Card>): BlackjackParticipant
    fun calculateBestValue(): Int
}
