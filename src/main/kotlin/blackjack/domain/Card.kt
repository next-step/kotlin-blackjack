package blackjack.domain

class Card private constructor (
    val suit: CardSuit,
    val number: CardNumber
) {
    companion object {
        private const val INVALID_CARD_ERROR = "존재하지 않는 카드입니다."
        val allCards =
            CardSuit.values().flatMap { suit ->
                CardNumber.values().map { number ->
                    Card(suit, number)
                }
            }

        // FIXME: list O(n) 탐색 vs map 생성
//        private val map =
//            allCards
//                .groupBy { it.suit }
//                .mapValues { it.value.associateBy { card -> card.number } }

        fun of(suit: CardSuit, number: CardNumber): Card =
            allCards.find { it.suit == suit && it.number == number } ?: throw IllegalArgumentException(INVALID_CARD_ERROR)
    }
}
