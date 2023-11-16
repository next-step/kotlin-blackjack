package blackjack.domain.card

data class Hand(
    private val _cards: MutableList<Card> = mutableListOf()
) {

    val cards: List<Card>
        get() = _cards.toList()

    fun add(card: Card) {
        _cards.add(card)
    }
}
