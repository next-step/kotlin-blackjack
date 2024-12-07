package blackjack.domain

class Card private constructor(val suit: Suit, val rank: Rank) {
    companion object {
        private val cardPool: Map<Pair<Suit, Rank>, Card> =
            Suit.entries.flatMap { suit ->
                Rank.entries.map { rank ->
                    (suit to rank) to Card(suit, rank)
                }
            }.toMap()

        fun getCard(suit: Suit, rank: Rank): Card {
            return cardPool[suit to rank] ?: throw IllegalArgumentException("Invalid card")
        }
    }
}
