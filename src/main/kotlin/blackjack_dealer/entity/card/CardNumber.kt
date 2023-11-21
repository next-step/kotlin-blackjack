package blackjack_dealer.entity.card

enum class CardNumber(val number: Int) {
    A(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    J(10), Q(10), K(10);

    companion object {
        fun findCardNumberFromNumber(number: Int): blackjack.entity.CardNumber {
            return blackjack.entity.CardNumber.values().find { cardNumber ->
                cardNumber.number == number
            } ?: error(UNEXPECTED_NUMBER)
        }

        private const val UNEXPECTED_NUMBER = "예상치 못한 값이 들어왔습니다."
    }
}
