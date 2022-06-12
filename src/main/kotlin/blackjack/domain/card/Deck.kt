package blackjack.domain.card

class Deck(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards get() = _cards.toList()

    fun draw(): Card {
        val selectedCard = cards.shuffled().first()
        _cards.remove(selectedCard)
        return selectedCard
    }

    companion object {
        fun default(): Deck {
            val cards = Suit.values()
                .flatMap { createCards(it) }
            return Deck(cards)
        }

        private fun createCards(pattern: Suit): List<Card> {
            val numberCards = NumberCard.NUMBER_CARDS
                .map { Card(pattern, it) }
            return numberCards +
                Card(pattern, King()) +
                Card(pattern, Queen()) +
                Card(pattern, Jack()) +
                Card(pattern, Ace())
        }
    }
}
