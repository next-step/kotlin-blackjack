package blackjack.model

class Dealer private constructor(name: Name, cards: Cards) : Gamer(name, cards) {

    override fun copy(name: Name, cards: Cards): Gamer = Dealer(name = name, cards = cards)

    override fun canReceive(): Boolean = cards.sum() <= RECEIVE_CARD_LIMIT

    companion object {
        private const val RECEIVE_CARD_LIMIT = 16

        fun from(name: Name, cards: Cards): Dealer = Dealer(name, cards)
    }
}
