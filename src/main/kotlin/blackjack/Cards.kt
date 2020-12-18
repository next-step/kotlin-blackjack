package blackjack

data class Cards(private val cards: MutableList<Card>) {

    fun getTotalScore(): Int {
        return cards.map { card -> card.getScore() }.sum()
    }

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun checkOver(card: Card): Boolean {
        return this.getTotalScore() + card.getScore() > WIN_SCORE
    }

    override fun toString(): String {
        return cards.map { card -> card.toString() }.toString()
    }

    companion object {
        const val WIN_SCORE = 21
    }
}
