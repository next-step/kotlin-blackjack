package blackjack.domain

class PokerCard(
    val symbol: PokerSymbol,
    val value: Int,
    val rank: String
) {
    fun representCard(): String {
        return rank + symbol.symbolName
    }
}
