package blackjack.domain.state.started.run

import blackjack.domain.card.Cards
import blackjack.domain.state.started.Started

abstract class Running(
    cards: Cards
) : Started(cards) {
    override val isRunning: Boolean = true
}
