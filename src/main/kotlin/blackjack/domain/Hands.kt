package blackjack.domain

class Hands(
    val cards: PlayingCards,
    private val isRunning: Boolean = true
) {
    val score: Score by lazy {
        Score.from(cards)
    }
    private val state: PlayerState by lazy {
        PlayerState.of(score, isRunning)
    }

    fun stay(): Hands = Hands(
        cards = cards,
        isRunning = false
    )

    fun isReceivable(): Boolean = !state.isFinished()

    operator fun plus(additionalCards: PlayingCards): Hands = Hands(cards + additionalCards)
}
