package game.blackjack.domain

import java.util.Stack

class Deck {
    private var cards: Stack<Card> = Stack()

    init {
        cards.addAll(
            Denomination.values().flatMap { rank ->
                Suit.values().map { Card(it, rank) }
            }.shuffled()
        )
    }

    fun draw(): Card {
        return cards.pop()
    }
}
