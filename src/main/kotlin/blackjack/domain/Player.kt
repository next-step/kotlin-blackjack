package blackjack.domain

class Player(val name: String) {
    var cards: List<Card> = listOf()

    fun addCard(card: Card) {
        cards = cards + card
    }
    fun addCards(cards: List<Card>) {
        this.cards = this.cards + cards
    }
}
