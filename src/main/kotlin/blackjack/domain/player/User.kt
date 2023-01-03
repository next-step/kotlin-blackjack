package blackjack.domain.player

import blackjack.domain.Bat
import blackjack.domain.state.State

class User(name: String, state: State) : Player(name, state) {
    lateinit var bat: Bat

    fun bat(money: Double) {
        bat = Bat(money)
    }

    fun profit(dealer: Dealer) = bat.profit(state, dealer)
}
