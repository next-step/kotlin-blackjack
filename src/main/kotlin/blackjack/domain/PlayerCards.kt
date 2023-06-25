package blackjack.domain

class PlayerCards(private val cards: MutableList<PlayingCard> = mutableListOf()) : Iterable<PlayingCard> by cards {

    fun add(vararg card: PlayingCard) {
        card.forEach { cards.add(it) }
    }

    override fun toString(): String {
        return "PlayerCards(cards=$cards)"
    }
}
