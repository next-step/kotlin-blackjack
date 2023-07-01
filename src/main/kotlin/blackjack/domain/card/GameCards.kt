package blackjack.domain.card

class GameCards(private val gameCards: MutableList<Card> = GameCardGenerator.crete()) {
    fun draw(): Card {
        return gameCards.removeFirstOrNull() ?: throw IllegalStateException(GAME_CARD_EXCEPTION)
    }

    companion object {
        private const val GAME_CARD_EXCEPTION = "게임 카드가 소진되었습니다"
    }
}
