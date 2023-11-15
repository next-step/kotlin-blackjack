package blackjack.model

import kotlin.random.Random

data class Card(
    val suit: Suit,
    val rank: Rank,
) {
    companion object {

        private val random = Random(System.currentTimeMillis())

        private val CARDS: Map<Suit, Map<Rank, Card>> by lazy {
            Suit.values().associateWith { suit ->
                Rank.values().associateWith { rank ->
                    Card(suit, rank)
                }
            }
        }

        fun of(): Card {
            val s = Suit.values()[random.nextInt(0, Int.MAX_VALUE) % Suit.values().size]
            val r = Rank.values()[random.nextInt(0, Int.MAX_VALUE) % (Rank.values().size)]
            return CARDS[s]!![r]!!
        }

        fun of(suit: Suit, rank: Rank): Card {
            return CARDS[suit]!![rank]!!
        }
    }
}
