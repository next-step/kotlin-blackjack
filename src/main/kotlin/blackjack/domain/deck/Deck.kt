package blackjack.domain.deck

class Deck private constructor(
    cards: List<Card>,
) {
    private val cards = cards.toMutableList()

    fun takeOut(): Card = cards.removeAt(FIRST)

    fun size(): Int = cards.size

    companion object {
        private const val FIRST = 0

        private val CACHED_DECK: List<Card> by lazy {
            Denomination.values().flatMap { denomination ->
                Suit.values().map {
                    Card(denomination, it)
                }
            }.shuffled()
        }

        fun init(): Deck {
            return Deck(CACHED_DECK)
        }
    }
}
