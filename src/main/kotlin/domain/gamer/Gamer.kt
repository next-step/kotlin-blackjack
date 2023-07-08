package domain.gamer

import domain.card.CardDeck
import domain.card.Cards

interface Gamer {
    val name: String

    val cards: Cards

    val isHit: Boolean

    val result: Int
        get() = cards.result()

    fun hit(cardDeck: CardDeck)
}
