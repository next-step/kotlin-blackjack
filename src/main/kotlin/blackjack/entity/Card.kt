package blackjack.entity

data class Card(
    val number: CardNumber,
    val shape: CardShape
) {
    override fun toString(): String {
        val cardNumber = number.number.takeIf { it in MINIMUM_NUMBER_NOT_CHARACTER..MAXIMUM_NUMBER_NOT_CHARACTER }
            ?: number.name
        return "${cardNumber}${shape.shapeName}"
    }

    companion object {
        private const val MINIMUM_NUMBER_NOT_CHARACTER = 2
        private const val MAXIMUM_NUMBER_NOT_CHARACTER = 9
    }
}
