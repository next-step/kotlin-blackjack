package blackjack.domain

import java.util.*

class Deck private constructor(private val cards: Queue<Card>) : Collection<Card> by cards {
    fun draw(): Card {
        require(cards.isNotEmpty()) { "더이상 뽑을 수 있는 카드가 없습니다." }

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

        fun create(): Deck {
            return create(ShufflingStrategy.NoShuffling)
        }

        fun create(shufflingStrategy: ShufflingStrategy): Deck {
            val shuffledCards = shufflingStrategy.shuffle(ALL_CARDS)
            return Deck(shuffledCards)
        }
    }
}