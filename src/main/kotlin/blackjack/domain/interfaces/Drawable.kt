package blackjack.domain.interfaces

import blackjack.domain.model.Card

interface Drawable {
    fun draw(card: Card)
}
