package blackjack.model.card

class Cards(
    private val cards: MutableList<Card> = mutableListOf(),
) {
    val size
        get() = cards.size

    fun addOne(card: Card) = cards.add(card)

    fun removeOne(): Card {
        validateNotEmpty()
        return cards.removeAt(0)
    }

    fun contains(card: Card) = cards.contains(card)

    fun containsAll(cards: List<Card>) = this.cards.containsAll(cards)

    private fun validateNotEmpty() = require(size > EMPTY_CARDS_SIZE) { "카드 개수가 0개 입니다." }

    companion object {
        private const val EMPTY_CARDS_SIZE = 0
    }
}
