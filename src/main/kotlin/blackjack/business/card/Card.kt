package blackjack.business.card

data class Card private constructor(val suit: Suit, val rank: Rank) {

    override fun toString(): String {
        return "${rank.printName}${suit.printName}"
    }

    companion object {

        val allCards: List<Card> = Suit.values().flatMap { suit ->
            Rank.values().map { rank ->
                Card(suit, rank)
            }
        }

        fun of(suit: Suit, rank: Rank): Card {
            return allCards.find { it.suit == suit && it.rank == rank } ?: throw IllegalArgumentException("잘못된 카드입니다.")
        }
    }
}
