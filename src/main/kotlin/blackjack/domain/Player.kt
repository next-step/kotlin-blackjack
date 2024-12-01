package blackjack.domain

data class Player(
    val name: String,
) {
    private val _cards: MutableList<Card> = mutableListOf()
    val cards: List<Card>
        get() = _cards

    fun addCard(card: Card) {
        _cards.add(card)
    }
}
