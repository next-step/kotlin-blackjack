package blackjack.domain

import blackjack.domain.extensions.deepCopy

class CardDeck {
    private val _cards: MutableList<Card> = setupCards()
    val cards: List<Card>
        get() = _cards.deepCopy()

    fun drawOne(): Card {
        return _cards.removeLast()
    }

    fun drawMany(drawCount: Int): List<Card> {
        return (1..drawCount).map { drawOne() }
    }

    private fun setupCards(): MutableList<Card> {
        val cards = CardSymbol.values().flatMap { symbol ->
            CardNumber.values().map { Card(symbol, it) }
        }
        return cards
            .shuffled()
            .toMutableList()
    }
}
