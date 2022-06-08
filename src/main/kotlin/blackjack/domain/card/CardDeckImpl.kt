package blackjack.domain.card

class CardDeckImpl : CardDeck {

    private val shape = listOf("하트", "스페이스", "클로버", "다이아몬드")
    private val number = listOf(ACE, "2", "3", "4", "5", "6", "7", "8", "9", JACK, QUEEN, KING)

    private var cardPool: MutableList<Card> = mutableListOf()

    init {
        for (i in shape) {
            for (j in number) cardPool.add(Card(i, j))
        }

        cardPool.shuffle()
    }

    override fun getOne(): Card {
        return cardPool.removeFirst()
    }

    companion object {
        const val ACE = "A"
        const val JACK = "J"
        const val QUEEN = "Q"
        const val KING = "K"
    }
}
