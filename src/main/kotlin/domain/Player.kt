package domain

class Player(val name: String, val blackjackCards: BlackjackCards = BlackjackCards()): CardOpenable {
    override fun openFirstRound(): List<Card> {
        return blackjackCards.getCards()
    }

    fun receiveCard(card: Card) = blackjackCards.receiveCard(card)

    fun score(): Int = blackjackCards.calculateScore()

    fun cardSize() = blackjackCards.getCards().size
}
