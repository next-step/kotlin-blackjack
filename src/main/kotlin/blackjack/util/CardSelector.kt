package blackjack.util

import blackjack.domain.Card
import blackjack.domain.Deck

abstract class CardSelector {
    abstract val deck: Deck

    fun getCard(): Card = deck.getCard()
}
