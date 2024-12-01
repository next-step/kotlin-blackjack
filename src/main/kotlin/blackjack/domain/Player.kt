package blackjack.domain

data class Player(val playerName: Name, private val hand: Hand = Hand()) {
    val isBust: Boolean
        get() = hand.isBust
    val totalCards: Deck
        get() = Deck(hand.totalCards)
    val name: String
        get() = playerName.value

    fun receive(cards: Deck) {
        hand.add(cards.values())
    }

    fun same(other: String): Boolean {
        return playerName.value == other
    }

    fun score(): Int {
        return hand.score
    }

    companion object {
        fun from(name: String): Player {
            return Player(playerName = Name(name))
        }
    }
}
