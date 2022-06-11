package blackjack.domain

class Hands(val cards: PlayingCards) {
    val score: Score by lazy {
        Score.from(cards)
    }

    operator fun plus(additionalCards: PlayingCards): Hands {
        return Hands(cards + additionalCards)
    }
}
