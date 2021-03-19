package blackjack.domain

internal class Card private constructor(val symbol: CardSymbol, val number: CardNumber) {

    val name: String
        get() {
            return number.displayName + symbol.krName
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Card

        if (symbol != other.symbol) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + number.hashCode()
        return result
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
