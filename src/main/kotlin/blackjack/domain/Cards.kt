package blackjack.domain

data class Cards(private val cards: MutableList<Card> = mutableListOf()) : List<Card> by cards {

    fun getScore(): Int = NumberType.getScore(this)

    fun countAce(): Int {
        return cards.count(Card::hasAce)
    }

    fun addCard(card: Card) {
        cards.add(card)
    }
}
