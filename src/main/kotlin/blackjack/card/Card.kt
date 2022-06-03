package blackjack.card

data class Card(
    val suit: CardSuit,
    val symbol: CardSymbol
) {

    fun count(sumOfOthers: Int): Int {
        return symbol.count(sumOfOthers)
    }

    override fun toString(): String {
        return "[${suit.displayName}]${symbol.displayName}"
    }
}
