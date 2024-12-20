package blackjack.domain

class EarningMoney(private val dealer: Participant) {
    fun calculate(participant: Participant.Player): Double {
        if (dealer.isBlackjack() && participant.isBlackjack()) {
            return participant.bettingAmount.toDouble()
        }

        if (participant.isBlackjack()) {
            return participant.bettingAmount * 1.5
        }

        return (participant.bettingAmount * -1).toDouble()
    }
}
