package blackjack.domain

data class Card private constructor(val number: CardNumber, val shape: CardShape) {
    companion object {
        private val cardCache: Map<Pair<CardNumber, CardShape>, Card> = buildCache()

        private fun buildCache(): Map<Pair<CardNumber, CardShape>, Card> {
            return CardNumber.entries.flatMap { number ->
                CardShape.entries.map { shape ->
                    (number to shape) to Card(number, shape)
                }
            }.toMap()
        }

        fun of(
            number: CardNumber,
            shape: CardShape,
        ): Card {
            return cardCache[number to shape]!!
        }
    }
}
