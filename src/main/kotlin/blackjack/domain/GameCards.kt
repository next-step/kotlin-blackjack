package blackjack.domain

class GameCards {
    private val gameCards: MutableList<Card> = mutableListOf()

    fun draw(): Card {
        var card = Card(getShape(), getChar())
        while (gameCards.contains(card)) {
            card = Card(getShape(), getChar())
        }
        gameCards.add(card)
        return card
    }

    private fun getShape(): Shape {
        return Shape.values().random()
    }

    private fun getChar(): Character {
        return Character.values().random()
    }
}
