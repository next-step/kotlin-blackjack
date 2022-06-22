package blackjack.card

data class Card(val suit: Suit, val symbol: CardSymbol) {
    fun isAceCard(): Boolean {
        return symbol == CardSymbol.ACE
    }
}
