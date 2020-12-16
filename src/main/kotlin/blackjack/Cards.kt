package blackjack

data class Cards(private val cards: MutableList<Card>) {

    fun getTotalScore(): Int {
        return cards.map { card -> card.getScore() }.sum()
    }

    fun addCard(card: Card) {
        cards.add(card)
    }
}
