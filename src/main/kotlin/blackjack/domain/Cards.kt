package blackjack.domain

import java.util.*

class Cards private constructor(private val cards: Queue<Card>) : Collection<Card> by cards {
    fun draw(): Card {
        if (cards.isEmpty()) {
            throw CannotDrawCardsException()
        }

        return cards.poll()
    }

    companion object {
        fun create(): Cards {
            val cards = Suit.entries.flatMap { suit ->
                Rank.entries.map { rank ->
                    Card(suit, rank)
                }
            }.toCollection(LinkedList())

            return Cards(cards)
        }
    }
}