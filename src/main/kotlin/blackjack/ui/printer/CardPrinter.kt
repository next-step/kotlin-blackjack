package blackjack.ui.printer

import blackjack.domain.Card
import blackjack.entity.CardNumber
import blackjack.ui.printer.CardPrinter.Companion.takeCardNumber

@JvmInline
value class CardPrinter(val printer: String) {
    companion object {
        private const val MINIMUM_NUMBER_NOT_CHARACTER = 2
        private const val MAXIMUM_NUMBER_NOT_CHARACTER = 9

        fun print(card: Card): String {
            val cardNumber = card.takeCardNumber()
            val resultText = "${cardNumber}${card.shape.shapeName}"
            return CardPrinter(resultText).printer
        }

        private fun Card.takeCardNumber() =
            number.number.takeIf { it in MINIMUM_NUMBER_NOT_CHARACTER..MAXIMUM_NUMBER_NOT_CHARACTER }
                ?: useNumberIfTen()

        private fun Card.useNumberIfTen() = if (number == CardNumber.TEN) number.number else number.name
    }
}
