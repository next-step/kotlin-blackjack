package blackjack.domain.card

class Card private constructor(
    val number: CardNumber,
    val suit: Suit,
) {
    companion object {
        private val cache: Map<Pair<CardNumber, Suit>, Card> =
            CardNumber.entries.flatMap { number ->
                Suit.entries.map { suit ->
                    Pair(Pair(number, suit), Card(number, suit))
                }
            }.toMap()

        val cached: List<Card> = cache.values.toList()

        fun of(
            number: CardNumber,
            suit: Suit,
        ) = cache[Pair(number, suit)] ?: throw IllegalStateException("Card does not exist in cache.")

        fun of(
            rawNumber: String,
            rawSuit: String,
        ) = of(CardNumber.fromName(rawNumber), Suit.fromName(rawSuit))
    }
}
