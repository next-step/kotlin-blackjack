package blackjack

object Dealer {
    private val cards = mutableListOf<Card>().apply {
        val numbers = CardNumber.values()
        val shapes = CardShape.values()

        numbers.forEach { number ->
            shapes.forEach { shape ->
                add(Card(number, shape))
            }
        }
    }.shuffled().toMutableList()

    fun popOneCard(): Card {
        return cards.removeLast()
    }
}
