package blackjack.util

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Deck

class FixedCardsSelector(vararg cardList: Card) : CardSelector {
    private val deck = Deck(Cards(cardList.toList()))
    override fun drawCard(): Card {
        return deck.drawCard()
    }
}
