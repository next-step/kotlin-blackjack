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

    fun isExceedWinScore(): Boolean {
        return calculateTotalScore() > BlackJackRule.WIN_SCORE
    }

    fun isNotExceedWinScore(): Boolean {
        return isExceedWinScore().not()
    }

    fun isScoreLargerThan(other: Participant): Boolean {
        return calculateTotalScore() > other.calculateTotalScore()
    }

    fun calculateTotalScore(): Int {
        return _cards.calculateTotalScore()
    }

    abstract fun canNotReceiveCard(): Boolean
}
