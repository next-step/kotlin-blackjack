package blackjack.domain

class Cards(private val cards: MutableList<Card> = mutableListOf()) : List<Card> by cards {
    fun remove(): Card = cards.removeAt(0)
    fun add(card: Card) = cards.add(card)
    fun deepCopy(): Cards = Cards(map(Card::copy).toMutableList())
}
