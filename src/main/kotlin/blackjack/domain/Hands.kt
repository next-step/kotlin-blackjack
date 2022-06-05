package blackjack.domain

class Hands private constructor(val cards: PlayingCards) {
    val score: Score by lazy {
        Score.from(cards)
    }

    operator fun plus(additionalCards: PlayingCards): Hands {
        return Hands(cards + additionalCards)
    }

    companion object {
        fun from(cards: PlayingCards): Hands {
            return Hands(cards)
        }
    }
}
