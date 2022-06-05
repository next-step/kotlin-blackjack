package blackjack.model

class PlayingCards(private val cards: MutableList<PlayingCard>) : List<PlayingCard> by cards {
    companion object {
        fun from(cards: List<PlayingCard>): PlayingCards {
            return PlayingCards(cards.toMutableList())
        }
    }
}
