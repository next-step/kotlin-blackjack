package blackjack.domain

private const val GAME_CARD_EXCEPTION = "게임 카드가 소진되었습니다"

class GameCards {
    private val gameCards: MutableList<Card> = Shape.values().flatMap { shape ->
        Character.values().map { char -> Card(shape, char) }
    }.shuffled().toMutableList()

    fun draw(): Card {
        return gameCards.removeFirstOrNull() ?: throw RuntimeException(GAME_CARD_EXCEPTION)
    }
}
