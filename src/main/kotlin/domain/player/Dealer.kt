package domain.player

import domain.card.Card
import domain.card.Cards
import domain.state.State

class Dealer(cards: Cards) : Player(name = NAME, cards = cards) {

    override fun draw(card: Card): State {
        return if (isDrawable()) {
            super.draw(card)
        } else {
            super.stop()
        }
    }

    private fun isDrawable(): Boolean = cards.sum <= DRAWABLE_CARD_SUM_MAX

    companion object {
        private const val NAME = "딜러"
        private const val DRAWABLE_CARD_SUM_MAX = 16
    }
}
