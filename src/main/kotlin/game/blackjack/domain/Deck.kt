package game.blackjack.domain

class Deck {
    val cards: ArrayDeque<Card> = ArrayDeque(allCards.shuffled())
    val size: Int get() = cards.size

    fun initialDraw(): List<Card> {
        return List(INITIAL_DRAW_COUNT) { drawOnce() }
    }

    fun drawOnce(): Card {
        return cards.removeFirstOrNull()
            ?: throw IllegalStateException("게임 덱의 카드를 모두 소진했습니다.")
    }

    companion object {
        private const val INITIAL_DRAW_COUNT: Int = 2
        private val allCards: List<Card> = CardShape.values().flatMap { shape ->
            CardNumber.values().map { Card(it, shape) }
        }
    }
}
