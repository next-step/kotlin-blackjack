package domain

class Dealer(val blackjackCards: BlackjackCards = BlackjackCards()) {
    fun receiveCard(card: Card) {
        blackjackCards.receiveCard(card)
    }

    fun score(): Int {
        return blackjackCards.calculateScore()
    }
}
