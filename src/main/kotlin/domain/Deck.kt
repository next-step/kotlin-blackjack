package domain

import enum.Rank
import enum.Suit
import java.util.ArrayDeque
import java.util.Collections.shuffle

class Deck {
    private val cards = ArrayDeque<Card>().apply {
        val allCards = Suit.values().flatMap { suit ->
            Rank.values().map { rank -> Card(suit, rank) }
        }
        shuffle(allCards)
        addAll(allCards)
    }

    fun drawCard(): Card? = if (cards.isNotEmpty()) cards.removeFirst() else null
}
