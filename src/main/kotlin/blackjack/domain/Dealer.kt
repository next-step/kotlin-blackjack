package blackjack.domain

data class Dealer(
    override val info: PlayerInfo = PlayerInfo(NAME, 0),
    override val cards: Cards = Cards.empty()
) : Player {

    override fun deal(deck: Deck): Player {
        validateDealCallOnce()
        return copy(cards = this.cards + deck.getDealCards())
    }

    override fun hit(deck: Deck): Player {
        validateCanHit()
        return copy(cards = this.cards + deck.fetchCard())
    }

    override fun canPlay() = cards.canDealerPlay()

    companion object {
        private const val NAME = "딜러"
    }
}
