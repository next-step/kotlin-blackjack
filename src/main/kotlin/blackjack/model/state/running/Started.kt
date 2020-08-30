package blackjack.model.state.running

import blackjack.model.card.Cards
import blackjack.model.state.State
import blackjack.model.state.finished.BlackJack

data class Started(override val cards: Cards = Cards(setOf())) : Running() {

    fun blackJackChecked(): State {
        if (cards.isBlackJack())
            return BlackJack(cards)

        return Hit(cards)
    }
}
