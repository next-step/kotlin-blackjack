package blackjack

class PlayerCards(private val cards: MutableList<PlayingCard> = mutableListOf()) : Iterable<PlayingCard> by cards {

    fun add(vararg card: PlayingCard) {
        card.forEach { cards.add(it) }
    }
}
