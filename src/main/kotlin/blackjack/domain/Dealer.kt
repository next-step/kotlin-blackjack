package blackjack.domain

class Dealer : Participant("딜러") {
    override fun canNotReceiveCard(): Boolean {
        val totalScore = calculateTotalScore()
        return totalScore > MAXIMUM_SCORE_TO_RECEIVE_CARD
    }

    companion object {
        const val MAXIMUM_SCORE_TO_RECEIVE_CARD = 16
    }
}
