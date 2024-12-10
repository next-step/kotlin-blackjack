package blackjack.domain

open class Player(val name: String) {
    private var hand: Hand = Hand()

    fun addCards(newCards: List<Card>) {
        hand = hand.addCards(newCards)
    }

    val cards: List<Card> get() = hand.getCards()
    val score: Int get() = hand.calculateScore()
}