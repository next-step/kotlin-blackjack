package blackjack.domain.player

import blackjack.domain.bet.Money
import blackjack.domain.card.Deck
import blackjack.domain.player.name.Name
import blackjack.domain.player.state.Ready
import blackjack.domain.player.state.State
import blackjack.strategy.draw.DrawStrategy

data class Gamer(
    override val name: Name,
    override val state: State = Ready(),
) : Player(name, state) {

    override fun profit(other: Player, money: Money): Double = state.profit(other.state, money)

    override fun stay(): Player = Gamer(name, state.stay())

    override fun draw(deck: Deck, drawStrategy: DrawStrategy): Player {
        var nowState = state
        drawStrategy.draw(deck).forEach { nowState = nowState.draw(it) }
        return Gamer(name, nowState)
    }
}
