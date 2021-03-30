package blackjack

data class Game(private val firstCard: Card, private val secondCard: Card) {
    val state: Int
        get() {
            var score = firstCard.number.value + secondCard.number.value

            if (firstCard.number == CardNumber.ACE || secondCard.number == CardNumber.ACE) {
                score += SUBTRACT_FIRST_AND_SECONDARY_ACE_SCORE
            }

            if (score < BLACK_JACK_SCORE) {
                return HIT
            } else if (score == BLACK_JACK_SCORE) {
                return BLACK_JACK
            }

            return UNDEFINED
        }

    companion object {
        private const val HIT = 1
        private const val UNDEFINED = 2
        private const val BLACK_JACK = 3
        private const val BLACK_JACK_SCORE = 21
        private const val SUBTRACT_FIRST_AND_SECONDARY_ACE_SCORE = 10
    }
}
