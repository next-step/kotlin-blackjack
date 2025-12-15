package domain

class Player(val name: String, val blackjackCards: BlackjackCards = BlackjackCards()) {
    fun receiveCard(card: Card) = blackjackCards.receiveCard(card)

    fun score(): Int = blackjackCards.calculateScore()

    fun cardSize() = blackjackCards.getCards().size
}
