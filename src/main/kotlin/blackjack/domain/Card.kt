package blackjack.domain

data class Card(val cardNumber: String, val cardType: CardType) {
    init {
        check(validCardNumber(cardNumber))
    }

    private fun validCardNumber(cardNumber: String): Boolean {
        return when (cardNumber) {
            CardNumber.CARD_ACE.number, CardNumber.CARD_KING.number, CardNumber.CARD_QUEEN.number, CardNumber.CARD_JACK.number -> true
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
        private val MINIMIUM = 2
        private val MAXIMIUM = 9
    }
}