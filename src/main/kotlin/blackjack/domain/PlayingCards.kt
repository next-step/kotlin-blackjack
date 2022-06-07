package blackjack.domain

class PlayingCards private constructor(private val cards: List<PlayingCard>) : List<PlayingCard> by cards {
    fun countOf(number: CardNumber): Int = count { playingCard ->
        playingCard.isCardOf(number)
    }

    operator fun plus(other: PlayingCards): PlayingCards = from(cards + other.cards)

    companion object {
        fun from(cards: List<PlayingCard>): PlayingCards = PlayingCards(cards)

        fun empty(): PlayingCards = PlayingCards(emptyList())
    }
}
