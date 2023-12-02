package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Score

class Player(val name: Name) {
    private val _cards: Cards = Cards(mutableSetOf())
    val cards = _cards
    var score: Int = 0
        private set

    fun receiveCard(card: Card) {
        _cards.add(card)
        score = Score(_cards).value
    }
}
