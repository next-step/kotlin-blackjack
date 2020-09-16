package blackjack

class Deck {
    var deck = DEFAULT_DECK

    companion object {
        val DEFAULT_DECK =
            Suit.values().flatMap { shape ->
                Denomination.values().map { denomination ->
                    Card(denomination, shape)
                }
            }
    }
}
