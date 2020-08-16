package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.card.Deck

data class Dealer(
    override val info: PlayerInfo = PlayerInfo(
        NAME,
        0
    ),
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

    override fun canPlay() = cards.canPlay() && cards.totalScore < DEALER_STOP_SCORE

    companion object {
        private const val NAME = "딜러"
        private const val DEALER_STOP_SCORE = 17
    }
}
