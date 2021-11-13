package blackjack.domain.card

data class Card(val symbol: Symbol, val type: Type) {

    fun isAce() = (symbol == Symbol.ACE)

    val score = symbol.score
}
