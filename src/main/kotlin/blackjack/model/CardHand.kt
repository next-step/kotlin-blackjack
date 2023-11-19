package blackjack.model

class CardHand(cards: List<Card>) {
    var cards: List<Card> = cards
        private set

    fun addCard(card: Card) {
        val newCards = cards.toMutableList()
        newCards.add(card)
        cards = newCards.toList()
    }

    companion object {
        const val FIRST_CARD_COUNT = 2
    }
}
