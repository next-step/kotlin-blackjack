package ui

import domain.CardNumber

enum class CardNumberView(val cardNumber: CardNumber, val ui: String) {

    TWO(CardNumber.TWO, "2"),
    THREE(CardNumber.THREE, "3"),
    FOUR(CardNumber.FOUR, "4"),
    FIVE(CardNumber.FIVE, "5"),
    SIX(CardNumber.SIX, "6"),
    SEVEN(CardNumber.SEVEN, "7"),
    EIGHT(CardNumber.EIGHT, "8"),
    NINE(CardNumber.NINE, "9"),
    TEN(CardNumber.TEN, "10"),
    KING(CardNumber.KING, "K"),
    QUEEN(CardNumber.QUEEN, "Q"),
    JACK(CardNumber.JACK, "잭"),
    ACE(CardNumber.ACE, "에이스"), ;

    companion object {
        fun valueOf(cardNumber: CardNumber): String = values().single { it.cardNumber == cardNumber }.ui
    }
}
