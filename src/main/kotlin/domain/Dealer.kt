package domain

class Dealer(val blackjackCards: BlackjackCards = BlackjackCards()): CardOpenable {
    override fun openFirstRound(): List<Card> {
        return blackjackCards.getCards().firstOrNull()?.let { listOf(it) }?: emptyList()
    }

    fun receiveCard(card: Card) = blackjackCards.receiveCard(card)

    fun score(): Int = blackjackCards.calculateScore()

    fun cardSize() = blackjackCards.getCards().size
}
