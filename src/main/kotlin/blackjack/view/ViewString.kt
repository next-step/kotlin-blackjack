package blackjack.view

import blackjack.domain.Card
import blackjack.domain.CardSpell
import blackjack.domain.CardSuit

fun Card.viewString(): String {
    return "${this.spell.viewString()}${this.suit.viewString()}"
}

fun CardSpell.viewString(): String {
    return when (this) {
        CardSpell.ACE -> "A"
        CardSpell.TWO -> "2"
        CardSpell.THREE -> "3"
        CardSpell.FOUR -> "4"
        CardSpell.FIVE -> "5"
        CardSpell.SIX -> "6"
        CardSpell.SEVEN -> "7"
        CardSpell.EIGHT -> "8"
        CardSpell.NINE -> "9"
        CardSpell.TEN -> "10"
        CardSpell.JACK -> "J"
        CardSpell.QUEEN -> "Q"
        CardSpell.KING -> "K"
    }
}

fun CardSuit.viewString(): String {
    return when (this) {
        CardSuit.CLUBS -> "클로버"
        CardSuit.HEARTS -> "하트"
        CardSuit.DIAMONDS -> "다이아몬드"
        CardSuit.SPADES -> "스페이드"
    }
}
