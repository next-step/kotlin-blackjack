package blackjack.domain

import blackjack.enums.Rank
import blackjack.enums.Symbol
import java.util.Stack

class Deck {

    private val deck: Stack<Card> = Stack()

    val cardCount: Int
        get() = deck.size

    init {
        Symbol.values().forEach { symbol ->
            Rank.values().forEach { rank ->
                deck.push(Card(rank, symbol))
            }
        }

        deck.shuffle()
    }

    fun drawCard(count: Int): Cards {

        val cards = Cards(listOf())

        repeat(count) {
            val card = deck.pop()
            cards.append(card)
        }

        return cards
    }
}
