package blackjack.domain.model.player

import blackjack.domain.model.Card
import blackjack.domain.model.Cards
import blackjack.domain.model.Name

abstract class AbstractPlayer {
    abstract val name: Name
    abstract val cards: Cards

    abstract fun shouldDraw(): Boolean
    abstract fun getFirstOpenedCards(): Cards
    fun draw(card: Card) = cards + card
}
