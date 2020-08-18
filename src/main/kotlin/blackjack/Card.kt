package blackjack

data class Card(
    private val shape: Symbol,
    private val number: Numbers
) {
    fun getCardScore(totalScore: Int = 0): Int {
        return Numbers.ofScore(totalScore, number)
    }

    override fun toString(): String {
        return "${number.shape}${shape.symbol}"
    }

    companion object {
        fun getInstances(): Card {
            return Symbol.values().flatMap { symbol ->
                Numbers.values().map { numbers ->
                    Card(symbol, numbers)
                }
            }.shuffled()[0]
        }
    }
}
