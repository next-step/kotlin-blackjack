package blackjack.domain.card

data class Card(
    val number: CardNumber,
    val symbol: CardSymbol
) {
    override fun toString(): String {
        return number.displayName + symbol.symbolName
    }
}
