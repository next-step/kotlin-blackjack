package blackjack.domain

data class Card(val number: String, val symbol: SYMBOL) {
    fun printCard(): String {
        return number + symbol.value
    }

    fun getCardNumber(number: String): Int {
        if (SpecialNumber.entries.any { it.name == number }) {
            return when (SpecialNumber.valueOf(number)) {
                SpecialNumber.K,
                SpecialNumber.Q,
                SpecialNumber.J ->
                    SpecialNumber.K.value

                SpecialNumber.A ->
                    SpecialNumber.A.value
            }
        }
        return number.toInt()
    }

    companion object {
        enum class SYMBOL(val value: String) {
            SPADE("스페이드"),
            HEART("하트"),
            DIAMOND("다이아몬드"),
            CLUB("클로버"),
        }

        enum class SpecialNumber(val value: Int) {
            A(1),
            K(10),
            Q(10),
            J(10),
        }
    }
}
