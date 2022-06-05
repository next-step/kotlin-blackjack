package blackjack.model

data class PlayingCard(
    val suit: Suit,
    val number: CardNumber
) {
    val text: String = "${number.text}${suit.text}"

    companion object {
        private val PLAYING_CARDS: List<PlayingCard> = Suit.values().flatMap { suit ->
            CardNumber.values().map { number ->
                PlayingCard(suit, number)
            }
        }

        fun all(): PlayingCards {
            return PlayingCards.from(PLAYING_CARDS)
        }
    }
}
