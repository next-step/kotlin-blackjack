package blackjack.domain

data class Card(val symbol: Symbol, private val number: CardNumber) {

    val score = number.score

    fun isAceCard() = number == CardNumber.Ace

    override fun toString(): String = "${number.symbol}${symbol.title}"
}
