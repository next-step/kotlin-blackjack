package blackjack.domain

class Hands private constructor(
    val cards: PlayingCards,
    private val isRunning: Boolean
) {
    private val state: PlayerState by lazy {
        PlayerState.of(cards, isRunning)
    }
    val score: Score
        get() = state.score

    fun stay(): Hands = Hands(
        cards = cards,
        isRunning = false
    )

    fun isReceivable(): Boolean = !state.isFinished()

    operator fun plus(additionalCards: PlayingCards): Hands = Hands(cards + additionalCards, isRunning)

    companion object {
        fun from(cards: PlayingCards): Hands = Hands(
            cards = cards,
            isRunning = true
        )
    }
}
