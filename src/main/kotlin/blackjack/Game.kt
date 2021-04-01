package blackjack

data class Game(private val cards: MutableList<Card>) {
    val state: States
        get() {
            var score = cards.sumBy { it.number.value }

            for (card in cards) {
                score = calculateAce(card, score)
            }

            if (score < BLACK_JACK_SCORE) {
                return States.HIT
            } else if (score == BLACK_JACK_SCORE) {
                return States.BLACK_JACK
            }

            return States.BUST
        }

    private fun calculateAce(card: Card, score: Int): Int {
        if (card.number == CardNumber.ACE && score + SUBTRACT_FIRST_AND_SECONDARY_ACE_SCORE <= BLACK_JACK_SCORE) {
            return SUBTRACT_FIRST_AND_SECONDARY_ACE_SCORE + score
        }

        return score
    }

    constructor(firstCard: Card, secondCard: Card) : this(mutableListOf(firstCard, secondCard))

    fun draw(card: Card) {
        cards.add(card)
    }

    companion object {
        private const val BLACK_JACK_SCORE = 21
        private const val SUBTRACT_FIRST_AND_SECONDARY_ACE_SCORE = 10
    }
}
