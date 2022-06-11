package blackjack.util

import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck

class CardDeckFake(private val cards: MutableList<Card>) : CardDeck {
    override fun getOne(): Card {
        return cards.removeFirst()
    }
}
