package blackjack.domain

open class Participant {
    private val cards = Cards()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getTotalValue(): Int {
        return cards.getTotalValue()
    }

    fun getCards(): List<Card> {
        return cards.asList()
    }
}
