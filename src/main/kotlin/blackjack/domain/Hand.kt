package blackjack.domain

@JvmInline
value class Hand(val cards: List<Card>) {

    val count: Int
        get() = cards.size

    fun addCard(newCard: Card) = Hand(cards + newCard)
}
