package blackjack

class Card {
    private val shape = getShape()
    private val number = getNumber()

    private fun getShape(): CardShapes {
        return CardShapes.values().map { it }.shuffled()[0]
    }

    private fun getNumber(): CardNumbers {
        return CardNumbers.values().map { it }.shuffled()[0]
    }
}
