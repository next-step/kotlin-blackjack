package blackjack.domain

class Player(private val name: PlayerName, private val hand: Hand) {
    fun addCard(card: Card) {
        hand.addCard(card)
    }

    fun displayHand(): String {
        return hand.getCards().joinToString(", ") { it.display() }
    }

    fun isDrawable(): Boolean {
        return hand.calculateBestTotal() != 0
    }

    fun calculateTotal(): Int {
        return hand.calculateBestTotal()
    }

    fun getName(): String {
        return name.value
    }

    companion object {
        fun createNew(
            playerName: PlayerName,
            cards: List<Card>,
        ): Player {
            return Player(playerName, Hand.createInitial(cards))
        }
    }
}
