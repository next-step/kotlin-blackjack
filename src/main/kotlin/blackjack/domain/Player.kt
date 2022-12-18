package blackjack.domain

open class Player(val name: String) {
    private val _hand: MutableList<Card> = mutableListOf()
    val hand: List<Card> = _hand

    fun score(): Int {
        val score = hand.sumOf { it.denomination.score }

        if (!hasAce()) {
            return score
        }

        return calculateScoreWithAce(score)
    }

    fun addCard(card: Card) {
        _hand.add(card)
    }

    open fun canDraw(): Boolean {
        return score() <= BURST_SCORE
    }

    fun result(dealer: Dealer): Result {
        if (dealer.score() > 21) {
            return Result.WIN
        }

        if (score() > 21) {
            return Result.LOSE
        }

        return if (score() > dealer.score()) Result.WIN else Result.LOSE
    }

    private fun calculateScoreWithAce(score: Int): Int {
        if (score <= 11) {
            return score + 10
        }

        return score
    }

    private fun hasAce(): Boolean {
        return hand.any { it.denomination == Denomination.ACE }
    }

    companion object {
        const val BURST_SCORE = 21
    }
}
