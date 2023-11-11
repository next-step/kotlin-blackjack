package blackjack.domain

enum class Symbol {
    SPADE, HEART, DIAMOND, CLUB
}

enum class Rank(val score: Int) {
    TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(10), QUEEN(10), KING(10), ACE(11),
}

data class Card(val symbol: Symbol, val rank: Rank)

infix fun Symbol.of(rank: Rank): Card = Card(this, rank)

infix fun Array<Symbol>.of(rank: Rank): List<Card> = map { it of rank }
