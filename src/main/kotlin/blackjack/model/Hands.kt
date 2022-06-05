package blackjack.model

@JvmInline
value class Hands(val value: PlayingCards) {
    fun score(): Score = Score.from(value)

    operator fun plus(additionalCards: PlayingCards): Hands {
        return Hands(value + additionalCards)
    }
}
