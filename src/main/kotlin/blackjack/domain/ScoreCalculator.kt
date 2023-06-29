package blackjack.domain

object ScoreCalculator {
    const val BLACKJACK_LIMIT = 21

    fun calculateScore(cards: Cards): Int {
        var sum = cards.sumOf { getCardScore(it) }
        if (cards.hasAce() && sum <= 11) {
            sum += 10
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
