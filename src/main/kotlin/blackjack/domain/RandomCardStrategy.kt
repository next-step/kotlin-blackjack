package blackjack.domain

class RandomCardStrategy : RandomStrategy {

    private val cards: MutableList<Card> = mutableListOf()

    init {
        generateRandom()
    }

    override fun generateRandom() {
        val shuffledDeck = Denomination.values()
            .flatMap { denomination ->
                Shape.values().map { shape ->
                    Card(denomination, shape)
                }
            }.shuffled()
        cards.addAll(shuffledDeck)
    }

    fun getCards(): List<Card> = cards
}
