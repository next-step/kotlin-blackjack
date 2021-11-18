package blackjack.model

class Dealer private constructor(
    cards: Cards
) : BlackjackPlayer(cards) {

    override fun copy(cards: Cards): BlackjackPlayer = Dealer(cards)

    override fun canReceive(): Boolean = cards.sum() <= RECEIVE_CARD_LIMIT

    companion object {
        private const val RECEIVE_CARD_LIMIT = 16

        fun of(cards: Cards): Dealer = Dealer(cards)
    }
}
