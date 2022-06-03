package blackjack

interface Card {
    val symbol: Symbol
    fun score(): Int
}
