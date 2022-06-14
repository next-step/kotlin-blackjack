package blackjack.domain

class CardDeck(deck: List<Card>) {
    private val deck = deck.toMutableList()

    init {
        require(deck.toSet().size == deck.size) { "중복된 카드는 포함될 수 없습니다." }
    }

    fun count(): Int = deck.size
    fun pop(): Card = deck.removeAt(0)

    companion object {
        fun new(shuffleStrategy: ShuffleStrategy = RandomShuffleStrategy): CardDeck {
            val allCards = Symbol.values().map { symbol ->
                CardNumber.values().map { number ->
                    Card(symbol, number)
                }
            }.flatten()

            return CardDeck(shuffleStrategy.shuffle(allCards))
        }
    }
}
