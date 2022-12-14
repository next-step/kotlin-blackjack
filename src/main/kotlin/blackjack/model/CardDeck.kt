package blackjack.model

class CardDeck() {
    var cards = Suit.values()
        .flatMap { suit -> Denomination.values().map { Card.of(suit, it) } }
        .toMutableList()
        private set

    fun getSingleCard(): Card {
        cards.shuffle()
        return cards.removeFirst()
    }

    fun getCards(count: Int): List<Card> {
        return List(count) { getSingleCard() }
    }
}
