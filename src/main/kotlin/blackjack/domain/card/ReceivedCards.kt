package blackjack.domain.card

data class ReceivedCards(private val receivedCards: MutableSet<Card>) {

    fun sumOfCards(): Int {
        return receivedCards.sumOf { it.number }
    }

    fun addCard(card: Card) {
        receivedCards.add(card)
    }

    fun countOfAceCard(): Int {
        return receivedCards.count { it is Card.AceCard }
    }

    fun count(): Int {
        return receivedCards.size
    }

    fun getCardDescription(): String {
        return receivedCards.joinToString(", ") { extractCardDescription(it) }
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
