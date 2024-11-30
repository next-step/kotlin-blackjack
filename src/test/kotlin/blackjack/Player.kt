package blackjack

class Player(val playerName: Name, private val hand: Hand = Hand()) {
    fun receive(cards: List<Card>) {
        hand.add(cards)
    }
    val isBust: Boolean
        get() = hand.isBust
    val totalCards: List<Card>
        get() = hand.totalCards
    val name: String
        get() = playerName.value

    companion object {
        fun from(name: String): Player {
            return Player(playerName = Name(name))
        }
    }
}
