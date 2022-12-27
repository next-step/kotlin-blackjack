package blackjack.domain.state

import blackjack.domain.card.Cards

interface PlayerAction {
    fun hit(cards: Cards): State

    fun stay(cards: Cards): State
}
