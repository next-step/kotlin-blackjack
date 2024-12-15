package blackjack.domain

class Dealer : Player("딜러") {
    override fun canNotReceiveCard(): Boolean {
        val totalScore = calculateTotalScore()
        return totalScore > MAXIMUM_SCORE_TO_RECEIVE_CARD
    }

    companion object {
        private const val MAXIMUM_SCORE_TO_RECEIVE_CARD = 16
    }
}
