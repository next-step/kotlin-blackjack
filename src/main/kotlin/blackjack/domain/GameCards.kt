package blackjack.domain

class GameCards {
    private val gameCards: MutableList<Pair<Shape, Character>> = mutableListOf()

    fun draw(): Card {
        var card = getShape() to getChar()
        while (gameCards.contains(card)) {
            card = getShape() to getChar()
        }
        gameCards.add(card)
        return Card(card.first, card.second)
    }

    private fun getShape(): Shape {
        return Shape.values().random()
    }

    private fun getChar(): Character {
        return Character.values().random()
    }
}