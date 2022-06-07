package blackjack.domain

class CardDeck {

    private val shape = listOf("하트", "스페이스", "클로버", "다이아몬드")
    private val number = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "J", "Q", "K")

    private var cardPool: MutableList<Card> = mutableListOf()

    init {
        for (i in shape) {
            for (j in number) cardPool.add(Card(i, j))
        }

        cardPool.shuffle()
    }

    fun getOne(): Card {
        return cardPool.removeFirst()
    }
}
