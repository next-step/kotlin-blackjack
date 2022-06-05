package blackjack.model

class PlayingCard private constructor(
    val suit: Suit,
    val number: CardNumber
) {
    val text: String = "${number.text}${suit.text}"

    companion object {
        private val ALL_PLAYING_CARDS: List<PlayingCard> = Suit.values().flatMap { suit ->
            CardNumber.values().map { number ->
                PlayingCard(suit, number)
            }
        }

        fun of(suit: Suit, number: CardNumber): PlayingCard {
            return ALL_PLAYING_CARDS.first { playingCard ->
                playingCard.suit == suit && playingCard.number == number
            }
        }

        fun all(): PlayingCards {
            return PlayingCards.from(ALL_PLAYING_CARDS)
        }
    }
}
