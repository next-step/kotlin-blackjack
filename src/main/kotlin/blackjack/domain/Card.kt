package blackjack.domain

data class Card(
    val cardShape: CardShape,
    val cardNumber: CardNumber,
) {
    override fun toString(): String {
        return cardNumber.description + cardShape.description
    }

    fun calculate(score: Int): Int {
        return cardNumber.calc(score)
    }
}
