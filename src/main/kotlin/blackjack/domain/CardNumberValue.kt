package blackjack.domain

/**
 * 카드의 계산 값
 */
@JvmInline
value class CardNumberValue(val value: Int) {
    companion object {
        private const val MINIMUM_CARD_VALUE = 1
        private const val MAXIMUM_CARD_VALUE = 11
        private const val ACE_DEFAULT_VALUE = 1
        const val ACE_EXTRA_VALUE = 10
        private const val ROYAL_FAMILY_VALUE = 10
        private const val WRONG_CARD_VALUE_MESSAGE = "잘못된 카드 값입니다.($MINIMUM_CARD_VALUE~$MAXIMUM_CARD_VALUE 입력)"

        private val numberPool = (MINIMUM_CARD_VALUE..MAXIMUM_CARD_VALUE)
            .associateWith { CardNumberValue(it) }

        operator fun get(value: Int): CardNumberValue {
            require(value in MINIMUM_CARD_VALUE..MAXIMUM_CARD_VALUE) { WRONG_CARD_VALUE_MESSAGE }
            return numberPool[value]!!
        }

        fun getValue(rank: String): CardNumberValue {
            if (CardNumber.isAce(rank)) {
                return CardNumberValue[ACE_DEFAULT_VALUE]
            }

            if (CardNumber.isRoyalFamily(rank)) {
                return CardNumberValue[ROYAL_FAMILY_VALUE]
            }

            return CardNumberValue[rank.toInt()]
        }
    }
}
