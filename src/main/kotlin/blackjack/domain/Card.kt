package blackjack.domain

data class Card(val symbol: Symbol, val type: Type) {

    fun isAce() = (symbol == Symbol.ACE)

    val score = symbol.score
}
