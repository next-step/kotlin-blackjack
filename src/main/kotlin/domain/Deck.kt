package domain

import enum.Rank
import enum.Suit

class Deck {
    private val cards: MutableList<Card> = Suit.values().flatMap { suit ->
        Rank.values().map { rank -> Card(suit, rank) }
    }.toMutableList().apply { shuffle() }

    fun drawCard(): Card? = if (cards.isNotEmpty()) cards.removeAt(0) else null
}
