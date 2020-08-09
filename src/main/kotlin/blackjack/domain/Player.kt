package blackjack.domain

class Player(val name: String) {
    var cards: List<Card> = emptyList()
    fun calculatePoint(): Int = 0

    fun getCard(card: Card) {
        val currentCards = cards.toMutableList()
        currentCards.add(card)
        cards = currentCards.toList()
    }
}
