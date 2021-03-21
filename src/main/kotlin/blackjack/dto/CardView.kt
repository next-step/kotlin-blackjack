package blackjack.dto

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Denomination
import blackjack.domain.Suit

data class CardView(val value: String) {
    constructor(card: Card) : this(denominationView(card.denomination) + suitView(card.suit))

    companion object {
        fun denominationView(denomination: Denomination): String {
            return when (denomination) {
                Denomination.ACE -> "A"
                Denomination.TWO -> "2"
                Denomination.THREE -> "3"
                Denomination.FOUR -> "4"
                Denomination.FIVE -> "5"
                Denomination.SIX -> "6"
                Denomination.SEVEN -> "7"
                Denomination.EIGHT -> "8"
                Denomination.NINE -> "9"
                Denomination.TEN -> "10"
                Denomination.JACK -> "J"
                Denomination.QUEEN -> "Q"
                Denomination.KING -> "K"
            }
        }

        fun suitView(suit: Suit): String {
            return when (suit) {
                Suit.CLUB -> "클로버"
                Suit.DIAMOND -> "다이아몬드"
                Suit.SPADE -> "스페이드"
                Suit.HEART -> "하트"
            }
        }
    }
}

fun Cards.toView() = this.elements.map(::CardView).map { it.value }
