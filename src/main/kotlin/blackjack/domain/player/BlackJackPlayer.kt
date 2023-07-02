package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class BlackJackPlayer(val name: PlayerName) {
    val cards: Cards = Cards(mutableSetOf())

    fun addCard(card: Card?) {
        if (card != null) {
            cards.addCard(card)
        }
    }

    fun getScore(): Int {
        if (cards.isBust()) {
            return ZERO_SCORE
        }
        return cards.getCardScore()
    }

    companion object {
        private const val ZERO_SCORE = 0
    }
}
