package blackjack.model

import kotlin.random.Random

data class Card(
    val suit: Suit,
    val cardRank: CardRank,
) {
    companion object {

        private val random = Random(System.currentTimeMillis())

        private val CARDS: Map<Suit, Map<CardRank, Card>> by lazy {
            Suit.values().associateWith { suit ->
                CardRank.values().associateWith { rank ->
                    Card(suit, rank)
                }
            }
        }

        fun of(): Card {
            val s = Suit.values()[random.nextInt(0, Int.MAX_VALUE) % Suit.values().size]
            val r = CardRank.values()[random.nextInt(0, Int.MAX_VALUE) % (CardRank.values().size)]
            return CARDS[s]!![r]!!
        }

        fun of(suit: Suit, cardRank: CardRank): Card {
            return CARDS[suit]!![cardRank]!!
        }
    }
}
