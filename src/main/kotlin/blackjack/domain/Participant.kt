package blackjack.domain

sealed class Participant(val name: String) {
    private val _cards: Cards = Cards()

    val cards: List<Card>
        get() = _cards.elements

    val score: Int
        get() = _cards.calculateTotalScore()

    abstract fun isDealer(): Boolean

    fun receive(vararg cards: Card) {
        _cards.addAll(*cards)
    }

    fun receive(card: Card) {
        _cards.add(card)
    }

    fun isBurst(): Boolean {
        return score > BlackjackRule.BLACKJACK_SCORE
    }

    fun isBlackjack(): Boolean {
        return cards.size == BlackjackRule.NUMBER_OF_CARDS_FOR_BLACKJACK
                && score == BlackjackRule.BLACKJACK_SCORE
    }

    fun isNotBlackjack(): Boolean {
        return isBlackjack().not()
    }

    fun isScoreEqualTo(other: Participant): Boolean {
        return score == other.score
    }

    fun isScoreLargerThan(other: Participant): Boolean {
        return score > other.score
    }

    abstract fun canNotReceiveCard(): Boolean
}
