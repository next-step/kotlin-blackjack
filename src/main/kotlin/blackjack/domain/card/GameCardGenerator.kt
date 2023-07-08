package blackjack.domain.card

class GameCardGenerator {
    fun crete(): GameCards = GameCards(CARDS.shuffled().toMutableList())

    companion object {
        private val CARDS: List<Card> = Shape.values().flatMap { shape ->
            Character.values().map { char -> Card(shape, char) }
        }
    }
}
