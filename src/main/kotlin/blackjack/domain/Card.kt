package blackjack.domain

data class Card(val cardType: CardType, val cardValue: CardValue) {
    fun name(): String {
        val value: String = when (cardValue.value) {
            in 2..10 -> cardValue.value
            else -> cardValue
        }.toString()
        return "$cardType $value"
    }
}
