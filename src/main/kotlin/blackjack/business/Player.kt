package blackjack.business

class Player(val name: String) {
    private val _cards = mutableListOf<Card>()

    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
