package blackjack.domain

class Card private constructor (
    val suit: CardSuit,
    val rank: CardRank
) {
    fun print(): String = rank.forOutput + suit.forOutput

    companion object {
        private const val INVALID_CARD_ERROR = "존재하지 않는 카드입니다."
        val allCards =
            CardSuit.values().flatMap { suit ->
                CardRank.values().map { rank ->
                    Card(suit, rank)
                }
            }

        // FIXME: list O(n) 탐색 vs map 생성
//        private val map =
//            allCards
//                .groupBy { it.suit }
//                .mapValues { it.value.associateBy { card -> card.rank } }

        fun of(suit: CardSuit, rank: CardRank): Card =
            allCards.find { it.suit == suit && it.rank == rank } ?: throw IllegalArgumentException(INVALID_CARD_ERROR)
    }
}
