package blackjack.domain

sealed class Participant(
    open val name: String,
    protected open val gameState: GameState,
) {
    val score: Int
        get() = gameState.score

    val handSize: Int
        get() = gameState.handSize

    val cards: String
        get() = gameState.cards

    val isBurst: Boolean
        get() = Rule.isBurst(score)

    val isBlackjack: Boolean
        get() = Rule.isBlackjack(score, handSize)

    val profit: Int
        get() = gameState.betValue

    open fun initialDraw(deck: Deck) {
        repeat(INITIAL_DRAW_COUNT) {
            hit(deck)
        }
    }

    fun hit(deck: Deck) {
        val card = deck.draw()
        gameState.addCard(card)
    }

    companion object {
        const val INITIAL_DRAW_COUNT = 2
    }
}
