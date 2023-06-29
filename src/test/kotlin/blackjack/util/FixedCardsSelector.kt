package blackjack.util

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Deck

class FixedCardsSelector(vararg cardList: Card) : CardSelector {
    private val deck = Deck(Cards(cardList.toList()))
    override fun drawCard(): Card {
        return deck.drawCard()
    }
}
