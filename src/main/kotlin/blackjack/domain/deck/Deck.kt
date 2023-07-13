package blackjack.domain.deck

import blackjack.domain.card.Card
import blackjack.domain.card.NumberShape
import blackjack.domain.card.Pattern

class Deck private constructor(private val cards: MutableSet<Card>) {

    fun getCards(): Set<Card> {
        return cards
    }

    fun popCard(): Card {
        return cards.first().also { cards.remove(it) }
    }

    companion object {
        fun makeDeck(): Deck {
            val cards = Pattern.values().flatMap { pattern ->
                NumberShape.values().map { number ->
                    Card(number, pattern)
                }
            }.shuffled().toMutableSet()
            return Deck(cards)
        }
    }
}
