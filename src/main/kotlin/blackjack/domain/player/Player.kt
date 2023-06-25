package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

class Player(val name: String, cards: Cards) {
    private var _cards = cards
    val cards
        get() = _cards

    val score
        get() = _cards.getScore()

    fun draw(card: Card) {
        _cards += card
    }

    fun draw(cards: Cards) {
        _cards += cards
    }

    fun isBustPlayer(): Boolean {
        return score.isBust()
    }

    fun isNotBustPlayer(): Boolean {
        return !score.isBust()
    }

    fun isBlackJack(): Boolean {
        return score.isBlackJack()
    }

    fun isNotBlackJack(): Boolean {
        return !score.isBlackJack()
    }
}
