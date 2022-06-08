package blackjack.domain.card

class CardDeckImpl : CardDeck {

    private val pattern = listOf(HEART, SPADE, CLUB, DIAMOND)
    private val number = listOf(ACE, "2", "3", "4", "5", "6", "7", "8", "9", JACK, QUEEN, KING)

    private var cardPool: MutableList<Card> = mutableListOf()

    init {
        for (i in pattern) {
            for (j in number) cardPool.add(Card(i, j))
        }

        cardPool.shuffle()
    }

    override fun getOne(): Card {
        return cardPool.removeFirst()
    }

    companion object {
        const val HEART = "하트"
        const val SPADE = "스페이드"
        const val CLUB = "클로버"
        const val DIAMOND = "다이아몬드"

        const val ACE = "A"
        const val JACK = "J"
        const val QUEEN = "Q"
        const val KING = "K"
    }
}
