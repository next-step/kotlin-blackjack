package blackjack.ui.model

import blackjack.domain.card.Cards
import blackjack.domain.player.Dealer

class DealerOutputModel(cards: Cards) : PlayerOutputModel(Dealer.NAME, cards) {
    override val cards: Cards
        get() = Cards(listOf(super.cards.cards.first()))
}
