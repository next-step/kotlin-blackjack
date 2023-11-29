package blackjack

data class CardValue(val value: Any) {
    init {
        require(value in CARD_RANGE) { "카드는 A, 2 ~ 10, J, Q, K만 가능합니다." }
    }

    fun getNumber(): Int {
        return when (value) {
            is Int -> value
            is StringValue -> value.number.first()
            else -> 0
        }
    }

    companion object {
        private const val CARD_MIN_VALUE = 2
        private const val CARD_MAX_VALUE = 10
        private val CARD_STRING_VALUE = StringValue.values().toList()
        private val CARD_RANGE = (CARD_MIN_VALUE..CARD_MAX_VALUE) + CARD_STRING_VALUE
        val CARD_Card_VALUES = CARD_RANGE.map(::CardValue)
    }
}

enum class StringValue(val number: List<Int>) {
    A(listOf(1, 11)),
    J(listOf(10)),
    Q(listOf(10)),
    K(listOf(10)),
    ;

    companion object {
        fun addAce(score: Int): Int {
            val max = score + A.number.last()
            return if (max <= BLACKJACK_VALUE) {
                max
            } else {
                max - A.number.last() + A.number.first()
            }
        }

        private const val BLACKJACK_VALUE = 21
    }
}