package blackjack

class Deck {
    var deck = DEFAULT_DECK

    fun shuffled() {
        deck = deck.shuffled()
    }

    fun getCard(): Card = deck.get(0)

    companion object {
        val DEFAULT_DECK =
            Suit.values().flatMap { shape ->
                Denomination.values().map { denomination ->
                    Card(denomination, shape)
                }
            }
    }
}
