package blackjack.domain

internal data class Card private constructor(val symbol: CardSymbol, val number: CardNumber) {

    val name: String
        get() {
            return number.displayName + symbol.krName
        }

    companion object {
        private val CACHE = CardSymbol.values()
            .flatMap { symbol -> CardNumber.values().map { grade -> symbol to grade } }
            .map { it to Card(it.first, it.second) }.toMap()

        val ALL_CARD: List<Card>
            get() = CACHE.values.toList()

        fun of(symbol: CardSymbol, number: CardNumber): Card {
            val pair = symbol to number
            return CACHE[pair] ?: throw IllegalArgumentException("invalid $symbol, $number")
        }
    }
}
