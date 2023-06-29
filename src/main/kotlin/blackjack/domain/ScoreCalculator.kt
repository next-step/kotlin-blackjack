package blackjack.domain

object ScoreCalculator {
    const val BLACKJACK_LIMIT = 21
    private const val ACE_BONUS_SCORE = 10

    fun calculateScore(cards: Cards): Int {
        var sum = cards.sumOf { getCardScore(it) }
        if (cards.hasAce() && sum + ACE_BONUS_SCORE <= BLACKJACK_LIMIT) {
            sum += ACE_BONUS_SCORE
        }
        return sum
    }

    private fun getCardScore(card: Card): Int {
        return when (card.cardNumber) {
            CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING -> 10
            else -> card.cardNumber.ordinal + 1
        }
    }
}
