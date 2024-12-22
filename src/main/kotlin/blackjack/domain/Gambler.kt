package blackjack.domain

class Gambler(name: String) : Participant(name) {
    override fun canNotReceiveCard(): Boolean {
        val totalScore = calculateTotalScore()
        return totalScore >= BlackjackRule.BLACKJACK_SCORE
    }

    fun determineResultStatus(dealer: Dealer): ResultStatus {
        return when {
            dealer.isExceedBlackjackScore() -> ResultStatus.WIN
            this.isExceedBlackjackScore() -> ResultStatus.DEFEAT
            this.isScoreLargerThan(dealer) -> ResultStatus.WIN
            this.isScoreEqualTo(dealer) -> ResultStatus.DRAW
            else -> ResultStatus.DEFEAT
        }
    }
}
