package blackjack.domain

data class Card(val cardNumber: CardNumber, val cardType: CardType) {
    init {
        check(validCardNumber(cardNumber))
    }

    private fun validCardNumber(cardNumber: CardNumber): Boolean {
        return when (cardNumber) {
            CardNumber.CARD_ACE.number -> true
            else -> {
                isAvailableNumber(cardNumber)
            }
        }
    }

    private fun isAvailableNumber(cardNumber: String): Boolean {

        val number = cardNumber.toIntOrNull() ?: return false
        return number in MINIMIUM..MAXIMIUM
    }

    companion object {
        private const val MINIMIUM = 2
        private const val MAXIMIUM = 10
    }
}