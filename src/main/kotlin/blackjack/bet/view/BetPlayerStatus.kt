package blackjack.bet.view

import blackjack.bet.domain.BetPlayer
import blackjack.bet.domain.Wallet

data class BetPlayerStatus(
    val name: String,
    val handRepresent: String,
    val optimalValue: Int,
    val wallet: Wallet,
) {
    companion object {
        fun of(player: BetPlayer): BetPlayerStatus {
            return BetPlayerStatus(player.name, player.showHands(), player.optimalValue(), player.wallet())
        }

        fun dealerUpCard(dealer: BetPlayer): BetPlayerStatus {
            return BetPlayerStatus(dealer.name, dealer.hands().first().representCard(), dealer.optimalValue(), dealer.wallet())
        }
    }
}
