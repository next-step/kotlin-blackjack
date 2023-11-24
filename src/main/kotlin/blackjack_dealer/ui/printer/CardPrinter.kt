package blackjack_dealer.ui.printer

import blackjack_dealer.entity.card.Card
import blackjack_dealer.entity.card.CardNumber

@JvmInline
value class CardPrinter(val printer: String) {
    companion object {
        private const val MINIMUM_NUMBER_NOT_CHARACTER = 2
        private const val MAXIMUM_NUMBER_NOT_CHARACTER = 9

        fun print(card: Card): String {
            val cardNumber = card.takeCardNumber()
            val resultText = "${cardNumber}${card.cardShape.shapeName}"
            return CardPrinter(resultText).printer
        }

        private fun Card.takeCardNumber() =
            cardNumber.number.takeIf { it in MINIMUM_NUMBER_NOT_CHARACTER..MAXIMUM_NUMBER_NOT_CHARACTER }
                ?: useNumberIfTen()

        private fun Card.useNumberIfTen() = if (cardNumber == CardNumber.TEN) cardNumber.number else cardNumber.name
    }
}
