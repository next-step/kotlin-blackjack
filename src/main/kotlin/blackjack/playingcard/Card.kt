package blackjack.playingcard

data class Card private constructor(val suit: Suit, val symbol: Symbol) {
    fun valueBy(sumOthers: Value): Value {
        return symbol.valueBy(sumOthers)
    }

    companion object {
        val ALL: List<Card> = Suit.values().flatMap { suit ->
            Symbol.values().map { symbol -> Card(suit, symbol) }
        }

        private val CACHE: Map<Suit, Map<Symbol, Card>> = ALL.groupBy { it.suit }
            .mapValues { (_, cards) -> cards.associateBy { it.symbol } }

        fun of(suit: Suit, symbol: Symbol): Card {
            return CACHE.getValue(suit).getValue(symbol)
        }
    }
}
