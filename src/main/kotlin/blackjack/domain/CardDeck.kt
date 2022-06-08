package blackjack.domain

object CardDeck {
    fun makeShuffledCard(): List<Card> {
        val numbers = CardNumber.values()
        val shapes = CardShape.values()
        val allCards = mutableListOf<Card>()

        numbers.forEach { number ->
            shapes.forEach { shape ->
                allCards.add(Card(number, shape))
            }
        }

        return allCards.shuffled()
    }
}