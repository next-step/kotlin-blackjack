package blackjack.domain

data class Cards(private val cards: MutableSet<Card> = mutableSetOf<Card>()) {

    fun getTotalScore(): Int {
        return this.cards.map { card -> card.getScore() }.sum()
    }

    fun amountOfCards(): Int {
        return cards.size
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun isMoreThanMax(): Boolean {
        return this.getTotalScore() >= WIN_SCORE
    }

    override fun toString(): String {
        return cards.map { card -> card.toString() }.toString()
    }

    companion object {
        const val WIN_SCORE = 21
    }
}
