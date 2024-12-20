package blackjack.domain

class WinningMoney(private val dealer: Participant, private val player: Participant.Player) {
    fun calculate(): Double {
        if (player.hasBusted()) return 0.0

        if (dealer.isBlackjack() && player.isBlackjack()) {
            return player.bettingAmount.toDouble()
        }

        if (player.isBlackjack()) {
            return player.bettingAmount * 1.5
        }

        return 0.0
    }
}
