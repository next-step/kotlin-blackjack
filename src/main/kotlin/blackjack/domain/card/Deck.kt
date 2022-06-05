package blackjack.domain.card

class Deck(cards: List<Card>) {
    private val _cards = cards.toMutableList()
    val cards get() = _cards.toList()

    fun draw(): Card {
        val selectedCard = cards.shuffled().first()
        _cards.remove(selectedCard)
        return selectedCard
    }
}
