package blackjack.domain

sealed class Participant(val name: String) {
    private val _cards: Cards = Cards()

    val cards: List<Card>
        get() = _cards.elements

    fun receive(vararg cards: Card) {
        _cards.addAll(*cards)
    }

    fun receive(card: Card) {
        _cards.add(card)
    }

    fun isExceedBlackjackScore(): Boolean {
        return calculateTotalScore() > BlackjackRule.BLACKJACK_SCORE
    }

    fun isNotExceedBlackjackScore(): Boolean {
        return isExceedBlackjackScore().not()
    }

    fun isScoreEqualTo(other: Participant): Boolean {
        return calculateTotalScore() == other.calculateTotalScore()
    }

    fun isScoreLargerThan(other: Participant): Boolean {
        return calculateTotalScore() > other.calculateTotalScore()
    }

    fun calculateTotalScore(): Int {
        return _cards.calculateTotalScore()
    }

    abstract fun canNotReceiveCard(): Boolean
}
