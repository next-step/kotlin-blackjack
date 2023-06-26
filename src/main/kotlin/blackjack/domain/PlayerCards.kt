package blackjack.domain

class PlayerCards(cards: List<PlayingCard> = listOf()) : Iterable<PlayingCard> {

    private val cards = cards.toMutableList()

    fun add(vararg card: PlayingCard) {
        card.forEach { cards.add(it) }
    }

    override fun iterator(): Iterator<PlayingCard> {
        return this.cards.iterator()
    }
}
