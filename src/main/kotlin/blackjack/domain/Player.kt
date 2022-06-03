package blackjack.domain

data class Player(val name: String) {
    private val cards: MutableList<Card> = mutableListOf()

    fun addCard(card: Card) {
        cards.add(card)
    }

    fun cardCount() = cards.size
}
