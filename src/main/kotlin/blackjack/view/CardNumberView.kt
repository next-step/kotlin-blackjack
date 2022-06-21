package blackjack.view

import blackjack.model.CardNumber

object CardNumberView {
    fun toString(cardNumber: CardNumber): String {
        return when (cardNumber) {
            CardNumber.Ace -> "A"
            CardNumber.Two -> "2"
            CardNumber.Three -> "3"
            CardNumber.Four -> "4"
            CardNumber.Five -> "5"
            CardNumber.Six -> "6"
            CardNumber.Seven -> "7"
            CardNumber.Eight -> "8"
            CardNumber.Nine -> "9"
            CardNumber.Ten -> "10"
            CardNumber.Jack -> "J"
            CardNumber.Queen -> "Q"
            CardNumber.King -> "K"
        }
    }
}
