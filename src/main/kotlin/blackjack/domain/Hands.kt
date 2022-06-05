package blackjack.domain

class Hands(val value: PlayingCards) {
    val score: Score by lazy {
        Score.from(value)
    }

    operator fun plus(additionalCards: PlayingCards): Hands {
        return Hands(value + additionalCards)
    }
}
