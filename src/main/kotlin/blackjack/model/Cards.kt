package blackjack.model

data class Cards(
    val cards: MutableSet<Card> = mutableSetOf()
) {
    fun add(card: Card) {
        cards.add(card)
    }

    companion object {
        fun init(): Cards {
            return Cards()
        }
    }
}
