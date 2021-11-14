package blackjack.domain.deck

class Cards {
    private val _value: MutableList<Card> = mutableListOf()
    val value = _value

    fun receiveCard(card: Card) {
        _value.add(card)
    }

    fun isBlackjack(): Boolean {
        return _value.size == BLACKJACK_DEAL_CARD_COUNT && calculateTotalScore() == BLACKJACK_WINNING_SCORE
    }

    fun isTwentyOne(): Boolean {
        return _value.size > BLACKJACK_DEAL_CARD_COUNT && calculateTotalScore() == BLACKJACK_WINNING_SCORE
    }

    fun isBust(): Boolean {
        return calculateTotalScore() > BLACKJACK_WINNING_SCORE
    }

    fun getTotalScore(): Int = calculateTotalScore()

    fun haveCards(): String = _value.joinToString { it.toString() }

    private fun calculateTotalScore(): Int {
        val aceCount = _value.count { it.isAce() }
        val currentScore = sumOfNotAce()
        return calculateAceCase(aceCount, currentScore)
    }

    private fun calculateAceCase(aceCount: Int, currentScore: Int): Int {
        if (currentScore < ACE_MAX_SCORE && aceCount == 1) {
            return currentScore + ACE_MAX_SCORE
        }
        if (currentScore < 10 && aceCount == 2) {
            return currentScore + 12
        }
        if (currentScore < 9 && aceCount == 3) {
            return currentScore + 13
        }
        if (currentScore < 8 && aceCount == 4) {
            return currentScore + 14
        }
        return currentScore + aceCount
    }

    private fun sumOfNotAce(): Int {
        return _value.asSequence()
            .filterNot { it.isAce() }
            .sumOf { it.getScore() }
    }

    companion object {
        private const val BLACKJACK_WINNING_SCORE = 21
        private const val BLACKJACK_DEAL_CARD_COUNT = 2
        private const val ACE_MAX_SCORE = 11
    }
}
