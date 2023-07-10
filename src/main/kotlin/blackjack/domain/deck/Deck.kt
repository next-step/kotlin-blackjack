package blackjack.domain.deck

import blackjack.domain.card.Card
import blackjack.domain.card.NumberShape
import blackjack.domain.card.Pattern

class Deck private constructor(val cards: MutableSet<Card>, private val drawStrategy: DrawStrategy) {

    fun getOneCard(): Card {
        return drawStrategy.draw(cards)
    }

    companion object {
        fun makeDeck(): Deck {
            val cards = Pattern.values().flatMap { pattern ->
                NumberShape.values().map { number ->
                    Card(number, pattern)
                }
            }.shuffled().toMutableSet()
            return Deck(cards, BlackJackDrawStrategy())
        }
    }
}
