package blackjack

data class Card(val suit: Suit, val symbol: Symbol) {
    companion object {
        val ALL: List<Card> = Suit.values().flatMap { suit ->
            Symbol.values().map { symbol -> Card(suit, symbol) }
        }
    }
}
