package blackjack.domain

class Hands private constructor(
    val cards: PlayingCards,
    isRunning: Boolean
) {
    val state: PlayerState by lazy {
        PlayerState.of(cards, isRunning)
    }

    fun stay(): Hands = Hands(
        cards = cards,
        isRunning = false
    )

    fun isReceivable(): Boolean = !state.isFinished()

    operator fun plus(additionalCards: PlayingCards): Hands = Hands(cards + additionalCards, !state.isFinished())

    companion object {
        fun from(cards: PlayingCards): Hands = Hands(
            cards = cards,
            isRunning = true
        )
    }
}
