package baclkjack.view

import baclkjack.domain.card.Card
import baclkjack.domain.card.Number
import baclkjack.domain.card.Suit
import baclkjack.domain.play.GameState

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

fun List<Card>.toCards(): String = this.map { "${it.number.toName()}${it.suit.toName()}" }.joinToString { it }

fun GameState.toResult(): String = when (this) {
    GameState.WIN -> "승"
    GameState.DRAW -> "무승부"
    GameState.LOSE -> "패"
}

fun Map<GameState, Int>.toResult(): String = this.map {
    StringBuilder().append("${it.value}").append(it.key.toResult())
}.joinToString(separator = " ")
