package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.card.Deck

data class Challenger(
    override val info: PlayerInfo,
    override val cards: Cards = Cards.empty()
) : Player {

    constructor(name: String, bettingMoney: Int) : this(PlayerInfo(name, bettingMoney))

    constructor(name: String) : this(name, 0)

    override fun deal(deck: Deck): Player {
        validateDealCallOnce()
        return copy(cards = this.cards + deck.getDealCards())
    }

    override fun hit(deck: Deck): Player {
        validateCanHit()
        return copy(cards = this.cards + deck.fetchCard())
    }

    override fun canPlay() = cards.canPlay()
}
