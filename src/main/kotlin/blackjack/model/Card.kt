package blackjack.model

import kotlin.random.Random

data class Card(
    val suit: Suit,
    val cardRank: CardRank,
) {
    companion object {

        private val random = Random(System.currentTimeMillis())

        private val CARDS: Map<Pair< Suit,CardRank>, Card> by lazy {
            Suit.values().flatMap { suit ->
                CardRank.values().map { rank ->
                    (suit to rank) to Card(suit, rank)
                }
            }.toMap()
        }

        fun of(): Card {
            val suit = Suit.values()[random.nextInt(0, Int.MAX_VALUE) % Suit.values().size]
            val cardRank = CardRank.values()[random.nextInt(0, Int.MAX_VALUE) % (CardRank.values().size)]
            return requireNotNull(CARDS[s to r]) {"입력된 suit=[$s] , cardRank=[$r] 는 찾을수 없습니다"}
        }

        fun of(suit: Suit, cardRank: CardRank): Card {
            return requireNotNull(CARDS[suit to cardRank]) {"입력된 suit=[$suit] , cardRank=[$cardRank] 는 찾을수 없습니다"}
        }
    }
}
