package blackjack.domain

class Player(val name: String) {
    var cards: List<Card> = emptyList()
    var isHit: Boolean = true

    fun calculatePoint(): Int = cards.sumBy { it.getPoint() }

    fun addCard(card: Card) {
        val currentCards = cards.toMutableList()
        currentCards.add(card)
        cards = currentCards.toList()
    }
}
