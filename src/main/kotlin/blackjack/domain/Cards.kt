package blackjack.domain

import java.util.*

class Cards private constructor(private val cards: Queue<Card>) : Collection<Card> by cards {
    fun draw(): Card {
        if (cards.isEmpty()) {
            throw CannotDrawCardsException()
        }

        return cards.poll()
    }

    override fun toString(): String {
        return cards.toString()
    }

    companion object {
        private val ALL_CARDS = Suit.entries.flatMap { suit ->
            Rank.entries.map { rank ->
                Card(suit, rank)
            }
        }.toList()

        fun create(): Cards {
            return create(ShufflingStrategy.NoShuffling)
        }

        fun create(shufflingStrategy: ShufflingStrategy): Cards {
            val shuffledCards = shufflingStrategy.shuffle(ALL_CARDS)
            return Cards(shuffledCards)
        }
    }
}