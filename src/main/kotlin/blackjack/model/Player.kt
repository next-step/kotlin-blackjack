package blackjack.model

class Player private constructor(
    val name: Name,
    cards: Cards
) : BlackjackPlayer(cards) {

    fun copy(name: Name = this.name, cards: Cards): Player = from(name, cards)

    override fun copy(cards: Cards): BlackjackPlayer = copy(name = name, cards = cards)

    override fun canReceive(): Boolean = true

    companion object {
        fun from(name: Name, cards: Cards): Player = Player(name, cards)
    }
}
