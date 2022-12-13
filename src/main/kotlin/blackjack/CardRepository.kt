package blackjack

class CardRepository {

    private val cards: List<Card> = createDefaultCards()
    private var index = 0

    fun getCard() = cards[index++]

    companion object {
        private const val MAX_RANGE_VALUE = 13
        private const val MIN_RANGE_VALUE = 1

        private fun createDefaultCards(): List<Card> {
            val cards = ArrayList<Card>()
            Card.CardShape.values().forEach { cardShape ->
                val shapeCards = (MIN_RANGE_VALUE..MAX_RANGE_VALUE).map {
                    Card(cardShape, CardNumber.create(it))
                }
                cards.addAll(shapeCards)
            }
            return cards.shuffled()
        }
    }
}
