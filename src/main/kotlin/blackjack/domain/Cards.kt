package blackjack.domain

class Cards(private val cards: MutableList<Card> = mutableListOf()) : List<Card> by cards {
    fun add(card: Card) {
        cards.add(card)
    }
}
