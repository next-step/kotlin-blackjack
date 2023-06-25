package util

import blackjack.domain.Card
import blackjack.util.CardSelector

class FixedCardSelector(private val card: Card) : CardSelector {
    override fun drawCard(): Card {
        return card
    }
}
