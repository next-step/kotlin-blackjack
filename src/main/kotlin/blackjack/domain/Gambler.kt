package blackjack.domain

class Gambler(name: String) : Participant(name) {
    override fun canNotReceiveCard(): Boolean {
        val totalScore = calculateTotalScore()
        return totalScore >= BlackJackRule.WIN_SCORE
    }

    fun isWin(dealer: Dealer): Boolean {
        return isNotExceedWinScore() && isScoreLargerThan(dealer)
    }
}
