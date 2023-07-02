package blackjack.domain

class Deck(private val cards: MutableList<Card>) {

    fun drawCard(): Card = cards.removeLast()

    fun size(): Int = cards.size

    companion object {
        private val cards = createCards()
        private fun createCards(): List<Card> {
            val cardNumbers = CardNumber.values()
            val cardShapes = CardShape.values()
            return cardNumbers.flatMap { number ->
                cardShapes.map { shape ->
                    Card(number, shape)
                }
            }
        }

        fun create(): Deck {
            val shuffledCards = cards.shuffled().toMutableList()
            return Deck(shuffledCards)
        }
    }
}
