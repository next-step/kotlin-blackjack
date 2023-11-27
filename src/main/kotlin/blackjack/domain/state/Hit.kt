package blackjack.domain.state

import blackjack.domain.Cards

class Hit(private val cards: Cards) : Running(cards) {
    fun stand(): Stand {
        return Stand(cards)
    }
}
