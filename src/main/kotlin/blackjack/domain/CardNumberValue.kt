package blackjack.domain

/**
 * 카드의 계산 값
 */
@JvmInline
value class CardNumberValue(val value: Int) {
    companion object {
        private const val MINIMUM_CARD_VALUE = 1
        private const val MAXIMUM_CARD_VALUE = 11
        private const val WRONG_CARD_VALUE_MESSAGE = "잘못된 카드 값입니다.($MINIMUM_CARD_VALUE~$MAXIMUM_CARD_VALUE 입력})"

        private val numberPool = IntRange(MINIMUM_CARD_VALUE, MAXIMUM_CARD_VALUE)
            .map { CardNumberValue(it) }
            .toList()

        private operator fun get(index: Int): CardNumberValue {
            require(index in MINIMUM_CARD_VALUE..MAXIMUM_CARD_VALUE) { WRONG_CARD_VALUE_MESSAGE }
            return numberPool[index - 1]
        }

        fun getValue(text: String, chooseExtraValue: Boolean = false): CardNumberValue {
            if (CardNumber.isAce(text)) {
                return getAceCardNumber(chooseExtraValue)
            }

            if (CardNumber.isRoyalFamily(text)) {
                return numberPool[9]
            }

            val value = text.toIntOrNull()
            require(value != null && value in MINIMUM_CARD_VALUE until MAXIMUM_CARD_VALUE) { WRONG_CARD_VALUE_MESSAGE }
            return numberPool[value - 1]
        }

        private fun getAceCardNumber(chooseExtraValue: Boolean): CardNumberValue {
            if (chooseExtraValue) {
                return numberPool[10]
            }

            return numberPool[0]
        }
    }
}
