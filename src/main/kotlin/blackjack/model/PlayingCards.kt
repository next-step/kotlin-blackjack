package blackjack.model

class PlayingCards private constructor(private val cards: MutableList<PlayingCard>) : List<PlayingCard> by cards {
    operator fun plus(other: PlayingCards): PlayingCards {
        return from(cards + other.cards)
    }

    companion object {
        fun from(cards: List<PlayingCard>): PlayingCards {
            return PlayingCards(cards.toMutableList())
        }
    }
}
