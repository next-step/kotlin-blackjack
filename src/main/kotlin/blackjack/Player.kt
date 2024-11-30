package blackjack

data class Player(val playerName: Name, private val hand: Hand = Hand()) {
    val isBust: Boolean
        get() = hand.isBust
    val totalCards: List<Card>
        get() = hand.totalCards
    val name: String
        get() = playerName.value

    fun receive(cards: List<Card>) {
        hand.add(cards)
    }

    fun same(other: Player): Boolean {
        return playerName == other.playerName
    }

    companion object {
        fun from(name: String): Player {
            return Player(playerName = Name(name))
        }
    }
}
