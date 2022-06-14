package blackjack

class Card(
    private val symbol: CardSymbol,
    private val number: CardNumber,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Card) return false

        if (symbol != other.symbol) return false
        if (number != other.number) return false

        return true
    }

    override fun hashCode(): Int {
        var result = symbol.hashCode()
        result = 31 * result + number.hashCode()
        return result
    }
}
