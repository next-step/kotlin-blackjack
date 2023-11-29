package blackjack.domain

class PlayerCards {

    private val cards = mutableListOf<Card>()
    val values: List<Card>
        get() = cards

    fun add(card: Card) {
        cards.add(card)
    }
}
