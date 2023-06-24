package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.score.Score

class Player(val name: String, cards: Cards) {
    private var _cards = cards
    val cards
        get() = _cards

    fun draw(card: Card) {
        _cards += card
    }

    fun getScore(): Score {
        return _cards.getScore()
    }
}
