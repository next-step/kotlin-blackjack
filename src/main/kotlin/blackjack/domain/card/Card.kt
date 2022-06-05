package blackjack.domain.card

data class Card(
    val suit: CardSuit,
    val symbol: CardSymbol
) {

    fun count(sumOfOthers: Int): Int {
        return symbol.count(sumOfOthers)
    }
}
