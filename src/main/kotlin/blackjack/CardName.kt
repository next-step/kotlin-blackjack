package blackjack

import blackjack.domain.card.Card
import blackjack.domain.card.CardSuit

object CardName {
    fun of(card: Card): String = "${card.numberString()}${card.suitString()}"

    private fun Card.numberString() = when (this) {
        is Card.Two -> "2"
        is Card.Three -> "3"
        is Card.Four -> "4"
        is Card.Five -> "5"
        is Card.Six -> "6"
        is Card.Seven -> "7"
        is Card.Eight -> "8"
        is Card.Nine -> "9"
        is Card.Ten -> "10"
        is Card.Jack -> "J"
        is Card.Queen -> "Q"
        is Card.King -> "K"
        is Card.Ace -> "A"
    }

    private fun Card.suitString() = when (suit) {
        CardSuit.DIAMOND -> "다이아몬드"
        CardSuit.CLOVER -> "클로버"
        CardSuit.HEART -> "하트"
        CardSuit.SPADE -> "스페이드"
    }
}
