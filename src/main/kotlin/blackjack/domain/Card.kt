package blackjack.domain

data class Card private constructor(val number: CardNumber, val shape: CardShape) {
    companion object {
        private const val INVALID_CARD_NUMBER_EXCEPTION_MESSAGE = "유효하지 않은 카드 숫자입니다."
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
            return cardCache[number to shape]
                ?: throw IllegalArgumentException(INVALID_CARD_NUMBER_EXCEPTION_MESSAGE)
        }
    }
}
