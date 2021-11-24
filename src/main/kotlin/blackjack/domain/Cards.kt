package blackjack.domain

data class Cards(private val cards: MutableList<Card> = mutableListOf()) : List<Card> by cards {

    fun getScore(): Int = cards.sumOf { it.numberType.score }

    fun countAce(): Int {
        return cards.count { it.numberType == NumberType.ACE }
    }

    fun addCard(card: Card) {
        cards.add(card)
    }
}
