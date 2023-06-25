package next.step.blackjack.domain.card

data class GameCards(private val cards: MutableList<Card>) {

    fun pop(): Card = cards.removeFirst()

    fun pop(n: Int): List<Card> = (1..n).map { cards.removeFirst() }

    fun size(): Int = cards.size

    companion object {
        private const val DEFAULT_DECK_CNT = 6

        fun of(cards: List<Card>): GameCards = GameCards(cards.toMutableList())

        fun shuffled(deckCnt: Int = DEFAULT_DECK_CNT): GameCards =
            GameCards((1..deckCnt).flatMap { deck() }.shuffled().toMutableList())

        private fun deck(): List<Card> = CardFace.values().flatMap { mapSymbols(it) }

        private fun mapSymbols(face: CardFace) = CardSymbol.values().map { Card.of(face, it) }
    }
}
