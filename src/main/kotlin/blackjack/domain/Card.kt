package blackjack.domain

data class Card(val number: String, val symbol: SYMBOL) {
    fun printCard(): String {
        return number + symbol.value
    }

    fun getCardNumber(number: String): Int {
        if (SPECIAL_NUMBER.entries.any { it.name == number }) {
            return when (SPECIAL_NUMBER.valueOf(number)) {
                SPECIAL_NUMBER.K,
                SPECIAL_NUMBER.Q,
                SPECIAL_NUMBER.J ->
                    SPECIAL_NUMBER.K.value

                SPECIAL_NUMBER.A ->
                    SPECIAL_NUMBER.A.value
            }
        }
        return number.toInt()
    }

    companion object {
        enum class SYMBOL(val value: String) {
            SPADE("스페이드"),
            HEART("하트"),
            DIAMOND("다이아몬드"),
            CLUB("클로버")
        }

        enum class SPECIAL_NUMBER(val value: Int) {
            A(1),
            K(10),
            Q(10),
            J(10),
        }
    }
}
