package blackjack.domain

import blackjack.domain.Card.Companion.SYMBOL
import blackjack.domain.Card.Companion.SpecialNumber

object CardDeck : CardDeckStrategy {
    val cards = mutableListOf<Card>()

    override fun generate() {
        cards.clear()
        SYMBOL.entries.forEach { symbol ->
            (2..10).map { it.toString() }.forEach { cards.add(Card(it, symbol)) }
            SpecialNumber.entries.forEach { card -> cards.add(Card(card.name, symbol)) }
        }
        cards.shuffle()
    }

    override fun drawCard(): Card {
        return cards.removeFirst()
    }
}
