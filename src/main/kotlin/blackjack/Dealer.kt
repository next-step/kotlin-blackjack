package blackjack

object Dealer {
    private val cards = makeAllCards().shuffled().toMutableList()

    fun popOneCard(): Card {
        return cards.removeLast()
    }

    private fun makeAllCards(): List<Card> {
        val numbers = CardNumber.values()
        val shapes = CardShape.values()
        val allCards = mutableListOf<Card>()

        numbers.forEach { number ->
            shapes.forEach { shape ->
                allCards.add(Card(number, shape))
            }
        }

        return allCards
    }
}
