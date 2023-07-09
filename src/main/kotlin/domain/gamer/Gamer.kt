package domain.gamer

import domain.card.CardDeck
import domain.card.Cards

interface Gamer {
    val name: String

    val cards: Cards

    val isHit: Boolean

    val score: Int
        get() = cards.score()

    fun hit(cardDeck: CardDeck)
}
