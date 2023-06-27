package baclkjack.domain.card

class Deck(cards: List<Card> = emptyList()) {

    private val cards = cards.toMutableList()

    init {
        require(cards.isNotEmpty()) {
            "덱을 생성할 카드가 없습니다."
        }
    }

    fun draw(): Card {
        require(cards.isNotEmpty()) {
            "덱에 카드가 없습니다."
        }
        return cards.removeFirst()
    }

    companion object {
        fun createDeck(): Deck = Deck(cards = Suit.values().flatMap { it.toCardList() }.shuffled())

        private fun Suit.toCardList() = Number.values().map { Card(this, it) }
    }
}
