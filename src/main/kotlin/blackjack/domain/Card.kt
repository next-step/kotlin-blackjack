package blackjack.domain

class Card private constructor(
    val number: String,
    val symbol: SYMBOL,
) {
    fun printCard(): String {
        return number + symbol.value
    }

    fun getCardNumber(number: String): Int {
        if (SpecialNumber.entries.none { it.name == number }) return number.toInt()

        return when (SpecialNumber.valueOf(number)) {
            SpecialNumber.K,
            SpecialNumber.Q,
            SpecialNumber.J ->
                SpecialNumber.K.value

            SpecialNumber.A ->
                SpecialNumber.A.value
        }
    }

    companion object {
        const val MIN_NUMBER = 2
        const val MAX_NUMBER = 10

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

        val cards: List<Card> =
            SYMBOL.entries.flatMap { symbol ->
                (MIN_NUMBER..MAX_NUMBER).map { number ->
                    Card(number.toString(), symbol)
                } + SpecialNumber.entries.map { special -> Card(special.name, symbol) }
            }

        fun createCard(
            number: String,
            symbol: String,
        ): Card {
            return isValidCard(number, symbol)
        }

        private fun isValidCard(
            number: String,
            symbol: String,
        ): Card {
            val ret = cards.find { it.number == number && it.symbol.value == symbol }
            require(ret != null) {
                "Don't make card. Please type valid card"
            }
            return ret
        }
    }
}
