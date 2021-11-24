package blackjack.domain

import blackjack.controller.GameController.BLACK_JACK_SCORE

data class Player(val name: String, val cards: Cards = Cards()) {

    fun hit(card: Card) {
        if (canHit()) {
            cards.addCard(card)
        }
    }

    fun canHit() = cards.getScore() < BLACK_JACK_SCORE

    fun nowScore() = cards.getScore()

    companion object {
        fun of(name: String, cards: Cards = Cards()): Player {
            return Player(name, cards)
        }
    }
}
