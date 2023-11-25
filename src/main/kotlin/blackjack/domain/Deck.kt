package blackjack.domain

class Deck private constructor(
    val cards: Cards
) {
    val cardSize
        get() = cards.size

    init {
        require(cards.size == Cards.TOTAL_SIZE) { "52장의 카드가 준비되어야 게임을 시작할 수 있습니다" }
    }

    fun draw(): Card {
        return cards.dec()
    }

    fun draw(size: Int): List<Card> {
        return List(size) { cards.dec() }
    }

    companion object {
        const val INITIAL_DEAL_SIZE = 2

        fun of(): Deck {
            val shuffledCards = Suit.values()
                .flatMap { suit ->
                    Denomination.values().map { denomination -> Card(suit, denomination) }.shuffled()
                }
            return Deck(Cards.from(shuffledCards))
        }
    }
}
