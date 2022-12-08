package domain

class CardDeck {

    private val cardNumbers = CardNumber.values()
    private val cardShapes = CardShape.values()

    fun draw(): Card {
        shuffle()
        return Card(cardNumbers[0], cardShapes[0])
    }

    private fun shuffle() {
        cardNumbers.shuffle()
        cardShapes.shuffle()
    }
}
