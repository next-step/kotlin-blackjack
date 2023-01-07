package blackjack.domain.player

import blackjack.domain.Bat
import blackjack.domain.card.Card
import blackjack.domain.state.State

data class User(override val name: String, override val state: State, val bat: Bat = Bat(0.0)) : Player(name, state) {
    fun bat(money: Double) = this.copy(name = name, state = state, bat = Bat(money))
    fun profit(dealer: Dealer) = bat.profit(state, dealer)
    override fun draw(card: Card) = this.copy(name = name, state = state.draw(card), bat = bat)
    override fun stay() = this.copy(name = name, state = state.stay(), bat = bat)
}
