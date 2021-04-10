package blackjack.domain.state.notstarted

import blackjack.domain.Cards
import blackjack.domain.state.State
import blackjack.domain.state.finished.BlackJack
import blackjack.domain.state.started.Hit

class NotStarted : State {
    fun takeTwoCards(cards: Cards): State {
        if (cards.isBlackjack) {
            return BlackJack(cards)
        }
        return Hit(cards)
    }
}
