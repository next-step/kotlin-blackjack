package blackjack.view

import blackjack.domain.card.Card
import blackjack.domain.card.ReceivedCards

object CardView {

    fun getCardDescription(receivedCards: ReceivedCards): String {
        return receivedCards.receivedCards.joinToString(", ") { extractCardDescription(it) }
    }

    private fun extractCardDescription(card: Card): String {
        return when (card) {
            is Card.AceCard -> "A${card.cardSuit.description}"
            is Card.JackCard -> "J${card.cardSuit.description}"
            is Card.QueenCard -> "Q${card.cardSuit.description}"
            is Card.KingCard -> "K${card.cardSuit.description}"
            is Card.BasicCard -> "${card.number}${card.cardSuit.description}"
        }
    }
}
