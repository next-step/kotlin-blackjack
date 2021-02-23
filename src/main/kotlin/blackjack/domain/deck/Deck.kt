package blackjack.domain.deck

import java.util.*

class Deck(val cards: Stack<Card>) {
    fun draw(): Card {
        require(cards.isNotEmpty())
        return cards.pop()
    }

    companion object {
        fun createDeck(): Deck {
            val cards = mutableListOf<Card>()
            Suit.values()
                .forEach {
                    cards.addAll(createCards(it))
                }
            cards.shuffle()
            val stack = Stack<Card>()
            stack.addAll(cards)
            return Deck(stack)
        }

        private fun createCards(suit: Suit): List<Card> {
            return Denomination.values()
                .map { Card(it, suit) }
                .toList()
        }
    }
}
