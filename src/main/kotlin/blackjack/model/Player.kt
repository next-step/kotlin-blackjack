package blackjack.model

class Player private constructor(name: Name, cards: Cards) : Gamer(name, cards) {

    override fun copy(name: Name, cards: Cards): Player = from(name, cards)

    override fun canReceive(): Boolean = true

    companion object {
        fun from(name: Name, cards: Cards): Player = Player(name, cards)
    }
}
