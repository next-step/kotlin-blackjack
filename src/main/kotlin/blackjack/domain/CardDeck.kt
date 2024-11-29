package blackjack.domain

import blackjack.domain.Card.Companion.SYMBOL

object CardDeck : CardDeckStrategy {
    val cards = mutableListOf<Card>()

    init {
        SYMBOL.entries.forEach { symbol ->
            (2..10).map { it.toString() }.forEach { cards.add(Card(it, symbol)) }
            listOf("A", "K", "Q", "J").forEach { cards.add(Card(it, symbol)) }
        }
        cards.shuffle()
    }

    override fun drawCard(): Card {
        return cards.removeFirst()
    }
}