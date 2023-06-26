package blackjack.util

import blackjack.domain.Card
import blackjack.domain.Deck

class FixedCardsSelector(vararg cardList: Card) : CardSelector {
    private val deck = Deck(cardList.toList())
    override fun drawCard(): Card {
        return deck.drawCard()
    }
}
