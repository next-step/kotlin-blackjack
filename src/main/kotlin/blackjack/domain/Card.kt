package blackjack.domain

data class Card(val suitType: SuitType, val numberType: NumberType) {

    companion object {
        private const val INVALID_CARD_TYPES_MESSAGE = "올바른 Card 타입들을 입력해주세요"

        private val ALL_CARDS = NumberType.values().flatMap { cardType ->
            SuitType.values().map { suitType ->
                suitType to cardType
            }
        }.map { it to Card(it.first, it.second) }.toMap()

        val CARD_LIST = ALL_CARDS.values.toList()

        fun of(suitType: SuitType, numberType: NumberType): Card {
            val key = suitType to numberType
            return ALL_CARDS[key] ?: throw IllegalArgumentException(INVALID_CARD_TYPES_MESSAGE)
        }
    }
}
