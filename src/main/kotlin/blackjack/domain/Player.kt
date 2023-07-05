package blackjack.domain

class Player {
    private val _cards = mutableListOf<Card>()
    val cards: List<Card>
        get() = _cards.toList()

    fun addCard(cards: List<Card>): Boolean = _cards.addAll(cards)
}
