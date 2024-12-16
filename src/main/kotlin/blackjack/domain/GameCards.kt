package blackjack.domain

import java.util.LinkedList
import java.util.Queue

class GameCards private constructor(private val deck: Queue<Card>) {
    fun drawCard(): Card {
        return deck.poll()
    }

    fun size(): Int {
        return deck.size
    }

    companion object {
        fun create(): GameCards {
            val allCards =
                Suits.entries.flatMap { suit ->
                    Ranks.entries.map { rank -> Card(rank, suit) }
                }
            return GameCards(LinkedList(allCards.shuffled()))
        }
    }
}
