package blackjack.model

@JvmInline
value class Hands(val value: PlayingCards) {
    operator fun plus(additionalCards: PlayingCards): Hands {
        return Hands(value + additionalCards)
    }
}
