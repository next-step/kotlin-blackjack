package domain.player

import domain.card.Card

class Dealer(card1: Card, card2: Card) : Player(name = NAME, card1 = card1, card2 = card2) {

    fun isDrawable(): Boolean = cards.sum <= DRAWABLE_CARD_SUM_MAX

    companion object {
        private const val NAME = "딜러"
        private const val DRAWABLE_CARD_SUM_MAX = 16
    }
}
