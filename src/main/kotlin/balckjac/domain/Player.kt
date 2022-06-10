package balckjac.domain

class Player(
    val name: String,
    cards: List<Card> = emptyList()
) {

    private val _cards = cards.toMutableList()
    val cards: List<Card> get() = _cards.toList()

    fun addCard(newCards: List<Card>) {
        _cards.addAll(newCards)
    }
}
