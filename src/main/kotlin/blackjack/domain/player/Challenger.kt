package blackjack.domain.player

import blackjack.domain.card.Cards
import blackjack.domain.card.Deck
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerInfo

data class Challenger(
    override val info: PlayerInfo,
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

    override fun canPlay() = cards.canPlay()
}
