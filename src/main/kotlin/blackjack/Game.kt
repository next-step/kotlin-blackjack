package blackjack

data class Game(private val firstCard: Card, private val secondCard: Card) {
    val state: States
        get() {
            var score = firstCard.number.value + secondCard.number.value

            if (firstCard.number == CardNumber.ACE || secondCard.number == CardNumber.ACE) {
                score += SUBTRACT_FIRST_AND_SECONDARY_ACE_SCORE
            }

            if (score < BLACK_JACK_SCORE) {
                return States.HIT
            } else if (score == BLACK_JACK_SCORE) {
                return States.BLACK_JACK
            }

            return States.UNDEFINED
        }

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val SUBTRACT_FIRST_AND_SECONDARY_ACE_SCORE = 10
    }
}
