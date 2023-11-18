package blackjack.ui.printer

import blackjack.domain.Card
import blackjack.entity.CardNumber

@JvmInline
value class CardPrinter(private val card: Card) {
    fun print(): String {
        val cardNumber = card.takeCardNumber()
        return "${cardNumber}${card.shape.shapeName}"
    }

    companion object {
        private const val MINIMUM_NUMBER_NOT_CHARACTER = 2
        private const val MAXIMUM_NUMBER_NOT_CHARACTER = 9

        private fun Card.takeCardNumber() =
            number.number.takeIf { it in MINIMUM_NUMBER_NOT_CHARACTER..MAXIMUM_NUMBER_NOT_CHARACTER }
                ?: useNumberIfTen()

        private fun Card.useNumberIfTen() = if (number == CardNumber.TEN) number.number else number.name
    }
}
