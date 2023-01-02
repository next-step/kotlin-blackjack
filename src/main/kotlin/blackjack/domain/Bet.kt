package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust

class Bet(val player: Player, private val money: Double) {
    fun profit(dealer: Dealer): Double {
        if (dealer.state is Blackjack && player.state is Blackjack) {
            return 0.0
        }

        if (dealer.state is Bust) {
            return player.profit(money)
        }

        return if (player.score() >= dealer.score()) player.profit(money) else -player.profit(money)
    }
}
