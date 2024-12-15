package blackjack.domain

class Dealer : Player("딜러") {
    override fun canNotReceiveCard(): Boolean {
        val totalScore = calculateTotalScore()
        return totalScore >= 17
    }
}
