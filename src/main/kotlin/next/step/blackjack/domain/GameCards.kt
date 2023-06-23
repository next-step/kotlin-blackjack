package next.step.blackjack.domain

data class GameCards(val cards: MutableList<Card>) {

    fun pop(): Card = cards.removeFirst()
    fun size(): Int = cards.size

    companion object {
        fun of(cards: List<Card>): GameCards = GameCards(cards.toMutableList())
    }
}
