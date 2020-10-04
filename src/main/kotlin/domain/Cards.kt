package domain

data class Cards(private val cards: MutableList<Card>) {

    fun getCards() = cards

    fun getTotalScore(): Int {
        return cards.map { it.score() }.sum()
    }

    fun addCard(card: Card): MutableList<Card> {
        cards.add(card)
        return getCards()
    }

    override fun toString(): String {
        return cards.map { it }.toString()
    }
}
