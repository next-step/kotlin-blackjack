package blackjack.domain.state.started.running

import blackjack.domain.card.Cards
import blackjack.domain.state.started.Started

abstract class Running(
    cards: Cards
) : Started(cards) {
    override val isRunning: Boolean
        get() = true
}
