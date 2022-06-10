package balckjac.domain

class Player(
    val name: String,
    cards: List<String> = emptyList()
) {

    private val _cards = cards.toMutableList()
    val cards: List<String> get() = _cards.toList()

    fun addCard(newCards: List<String>) {
        _cards.addAll(newCards)
    }
}
