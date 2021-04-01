package blackjack

class Cards(private val cards: List<Card>): List<Card> by cards {
    val score: Int
        get() {
            val score = cards.sumBy { it.number.value }

            for (card in cards) {
                return calculateAce(card, score)
            }
            return score
        }

    private fun calculateAce(card: Card, score: Int): Int {
        if (card.number == CardNumber.ACE && score + SUBTRACT_FIRST_AND_SECONDARY_ACE_SCORE <= Game.BLACK_JACK_SCORE) {
            return SUBTRACT_FIRST_AND_SECONDARY_ACE_SCORE + score
        }

        return score
    }

    companion object {
        private const val SUBTRACT_FIRST_AND_SECONDARY_ACE_SCORE = 10
    }
}
