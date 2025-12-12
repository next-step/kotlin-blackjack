package domain

class Player(val name: String, val backjackCards: BackjackCards = BackjackCards()) {
    fun receiveCard(card: Card) {
        backjackCards.receiveCard(card)
    }

    fun score(): Int {
        return backjackCards.calculateScore()
    }
}
