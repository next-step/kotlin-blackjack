package blackjack.domain

class Hands private constructor(
    val cards: PlayingCards,
    private val isRunning: Boolean
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

    operator fun plus(additionalCards: PlayingCards): Hands = Hands(cards + additionalCards, isRunning)

    companion object {
        fun create(): Hands = Hands(
            cards = PlayingCards.empty(),
            isRunning = true
        )
    }
}
