package blackjack.domain.card

class Cards(private val cards: MutableList<Card> = mutableListOf()) {
    fun add(card: Card) {
        cards.add(card)
    }

    fun getCards(): List<Card> = cards

    fun sum(): Int = cards.sumOf { it.number.value }

    companion object {
        const val MAX_CARDS_NUM_SUM = 21
    }
}
