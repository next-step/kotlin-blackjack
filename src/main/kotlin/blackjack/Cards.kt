package blackjack

data class Cards(private val cards: MutableSet<Card> = mutableSetOf<Card>()) {

    fun getTotalScore(): Int {
        return this.cards.map { card -> card.getScore() }.sum()
    }

    fun addCard(card: Card) {
        cards.add(card)
    }


    fun hasMoreThanOver(): Boolean {
        return this.getTotalScore() >= WIN_SCORE
    }

    override fun toString(): String {
        return cards.map { card -> card.toString() }.toString()
    }


    companion object {
        const val WIN_SCORE = 21
    }
}
