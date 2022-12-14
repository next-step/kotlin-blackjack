package blackjack.model

class CardDeck() {
    var cards = Suit.values()
        .flatMap { suit -> Denomination.values().map { Card(suit, it) } }
        .toMutableList()
        private set

    fun getSingleCard(): Card {
        cards.shuffle()
        return cards.removeFirst()
    }
}
