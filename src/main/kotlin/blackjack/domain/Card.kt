package blackjack.domain

interface Card {
    val symbol: Symbol
    fun score(): Score
}
