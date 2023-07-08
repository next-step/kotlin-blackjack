package baclkjack.view

import baclkjack.domain.card.Cards
import baclkjack.domain.card.Number
import baclkjack.domain.card.Suit

fun Suit.toName(): String = when (this) {
    Suit.SPADE -> "스페이스"
    Suit.HEART -> "하트"
    Suit.DIAMOND -> "다이아몬드"
    Suit.CLUB -> "클로버"
}

fun Number.toName(): String = when (this) {
    Number.ACE -> "A"
    Number.JACK -> "J"
    Number.QUEEN -> "Q"
    Number.KING -> "K"
    else -> this.value.toString()
}

fun Cards.toCards(): String = cards.map { "${it.number.toName()}${it.suit.toName()}" }.joinToString { it }
