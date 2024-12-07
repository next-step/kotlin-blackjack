package blackjack.view

import blackjack.domain.CardNumber
import blackjack.domain.CardSuit
import blackjack.domain.DrawCard

class CardExpression
private constructor(
    val value: String,
) {

    companion object {
        fun of(
            drawCard: DrawCard,
        ): CardExpression =
             CardExpression("${convertNumber(drawCard.number)}${convertSuit(drawCard.suit)}")

        private fun convertSuit(suit: CardSuit): String =
            when (suit) {
                CardSuit.HEARTS -> "하트"
                CardSuit.DIAMONDS -> "다이아몬드"
                CardSuit.CLUBS -> "클로버"
                CardSuit.SPADES -> "스페이드"
            }

        private fun convertNumber(number: CardNumber): String =
            when (number) {
                CardNumber.ACE -> "A"
                CardNumber.TWO -> "2"
                CardNumber.THREE -> "3"
                CardNumber.FOUR -> "4"
                CardNumber.FIVE -> "5"
                CardNumber.SIX -> "6"
                CardNumber.SEVEN -> "7"
                CardNumber.EIGHT -> "8"
                CardNumber.NINE -> "9"
                CardNumber.TEN -> "10"
                CardNumber.JACK -> "J"
                CardNumber.QUEEN -> "Q"
                CardNumber.KING -> "K"
            }
    }
}
