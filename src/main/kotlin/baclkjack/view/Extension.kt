package baclkjack.view

import baclkjack.domain.card.Card
import baclkjack.domain.card.Suit

fun Suit.toName() = when (this) {
    Suit.SPADE -> "스페이스"
    Suit.HEART -> "하트"
    Suit.DIAMOND -> "다이아몬드"
    Suit.CLUB -> "클로버"
}

fun List<Card>.toCards() = this.map { "${it.number.value}${it.suit.toName()}" }.joinToString { it }
