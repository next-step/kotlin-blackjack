package blackjack.domain

enum class Symbol {
    SPADE, HEART, DIAMOND, CLUB
}

enum class Rank(val score: Int) {
    TWO(2), THREE(3), FOUR(4), FIVE(5),
    SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(10), QUEEN(10), KING(10), ACE(11),
}

data class Card(val symbol: Symbol, val rank: Rank) {

    companion object {
        val ALL_CARDS: List<Card> = Rank.values().flatMap { rank -> Symbol.values() withRank rank }
    }
}

infix fun Symbol.withRank(rank: Rank): Card = Card(this, rank)

infix fun Array<Symbol>.withRank(rank: Rank): List<Card> = map { it withRank rank }
