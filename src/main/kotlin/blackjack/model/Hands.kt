package blackjack.model

@JvmInline
value class Hands(val value: List<PlayingCard>) {
    operator fun plus(additionalCards: List<PlayingCard>): Hands {
        return Hands(value + additionalCards)
    }
}
