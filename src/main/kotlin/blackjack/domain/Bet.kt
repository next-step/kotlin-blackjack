package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust

class Bet(val player: Player, private val money: Double) {
    fun profit(dealer: Dealer): Double {
        return when {
            dealer.state is Blackjack && player.state is Blackjack -> 0.0
            player.state is Blackjack -> money * 1.5
            dealer.state is Bust -> money
            player.state is Bust -> -money
            player.score() >= dealer.score() -> money
            else -> -money
        }
    }
}
