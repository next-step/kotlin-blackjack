package blackjack.domain

data class Player(val name: String) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards.deepCopy()

    fun takeCards(vararg cards: Card) {
        _cards.addAll(cards)
    }

    private fun List<Card>.deepCopy() = this.map { it.copy() }
}
