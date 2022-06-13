package blackjack.domain.card

class RandomCardDeck : CardDeck {

    private val cardPool: MutableList<Card> =
        PATTERNS.flatMap { pattern ->
            NUMBERS.map { number ->
                Card(pattern, number)
            }
        }
            .shuffled()
            .toMutableList()

    override fun getOne(): Card {
        return cardPool.removeFirst()
    }

    companion object {
        private const val HEART = "하트"
        private const val SPADE = "스페이드"
        private const val CLUB = "클로버"
        const val DIAMOND = "다이아몬드"

        const val ACE = "A"
        const val JACK = "J"
        const val QUEEN = "Q"
        const val KING = "K"

        private val PATTERNS = listOf(HEART, SPADE, CLUB, DIAMOND)
        private val NUMBERS = listOf(ACE, "2", "3", "4", "5", "6", "7", "8", "9", JACK, QUEEN, KING)
    }
}
