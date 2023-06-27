package blackjack.domain

class PlayerCards(private val blackJackRule: BlackJackRule = BlackJackRule(), cards: List<PlayingCard> = listOf()) :
    Iterable<PlayingCard> {

    private val cards = cards.toMutableList()

    fun add(vararg card: PlayingCard) {
        card.forEach { cards.add(it) }
    }

    fun point(): Int {
        return blackJackRule.point(this)
    }

    override fun iterator(): Iterator<PlayingCard> {
        return this.cards.iterator()
    }
}
