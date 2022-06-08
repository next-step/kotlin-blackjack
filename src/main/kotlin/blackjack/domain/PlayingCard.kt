package blackjack.domain

class PlayingCard private constructor(
    val suit: Suit,
    val number: CardNumber
) {
    val text: String = "${number.text}${suit.text}"

    fun isCardOf(number: CardNumber): Boolean = this.number == number

    companion object {
        private val ALL_PLAYING_CARDS: List<PlayingCard> = Suit.values().flatMap { suit ->
            CardNumber.values().map { number ->
                PlayingCard(suit, number)
            }
        }

        fun of(suit: Suit, number: CardNumber): PlayingCard = ALL_PLAYING_CARDS.first { playingCard ->
            playingCard.suit == suit && playingCard.number == number
        }

        fun all(): PlayingCards = PlayingCards.from(ALL_PLAYING_CARDS)
    }
}
