package baclkjack.domain.card

class Deck(cards: List<Card> = emptyList()) {

    init {
        require(cards.isNotEmpty()) {
            "덱을 생성할 카드가 없습니다."
        }
    }

    private val cards = cards.toMutableList()
    fun draw() = cards.removeFirst()

    companion object {
        fun createDeck(): Deck = Deck(cards = Suit.values().flatMap { suit ->
            Number.values().map { number ->
                Card(suit, number)
            }
        }.shuffled())
    }
}
