package blackjack.domain

class Cards(cards: Set<Card>) {

    private val cards: MutableSet<Card> = cards.toMutableSet()

    fun getCards() = cards

    fun size() = cards.size

    fun getTotalScore(): Int {
        return cards.map { it.score() }.sum()
    }

    fun addCard(card: Card): Set<Card> {
        cards.add(card)
        return getCards()
    }

    override fun toString(): String {
        return cards.map { it }.toString()
    }

    fun isMoreThanMaxNumber(number:Int): Boolean {
        return number <= MAXIMUM_CARDS_NUMBER
    }

    companion object{
        private const val MAXIMUM_CARDS_NUMBER = 21
    }

}
