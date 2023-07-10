package blackjack.domain.deck

import blackjack.domain.card.Card
import blackjack.domain.card.NumberShape
import blackjack.domain.card.Pattern

class Deck(val cards: MutableSet<Card>, private val drawStrategy: DrawStrategy) {

    fun getOneCard(): Card {
        return drawStrategy.draw(cards)
    }

    companion object {
        fun makeDeck(drawStrategy: DrawStrategy = BlackJackDrawStrategy()): Deck {
            val cards = Pattern.values().flatMap { pattern ->
                NumberShape.values().map { number ->
                    Card(number, pattern)
                }
            }.shuffled().toMutableSet()
            return Deck(cards, drawStrategy)
        }
    }
}
