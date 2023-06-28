package blackjack.domain

class Cards(private val cards: MutableSet<Card>) : MutableSet<Card> by cards {
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getTotalScore(): Int {
        return cards.sumOf {
            it.cardNumber.score
        }
    }

    fun getCardsSortByScore(): List<Card> {
        return cards.sortedBy { it.cardNumber.score }
    }
}
