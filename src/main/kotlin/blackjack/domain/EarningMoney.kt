package blackjack.domain

class EarningMoney(private val dealer: Participant) {
    fun calculate(player: Participant.Player): Double {
        if (dealer.hasBusted() && player.hasBusted().not()) {
            return (player.bettingAmount * 2).toDouble()
        }

        if (dealer.isBlackjack() && player.isBlackjack()) {
            return player.bettingAmount.toDouble()
        }

        if (player.isBlackjack()) {
            return player.bettingAmount * 1.5
        }

        return (player.bettingAmount * -1).toDouble()
    }
}
