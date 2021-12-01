package blackjack.domain.player

import blackjack.domain.bet.Money
import blackjack.domain.card.Deck
import blackjack.domain.player.name.Name
import blackjack.domain.player.state.Hit
import blackjack.domain.player.state.Ready
import blackjack.domain.player.state.State
import blackjack.strategy.draw.DrawStrategy

data class Dealer(
    override val name: Name = DEFAULT_NAME,
    override val state: State = Ready(),
) : Player(name, state) {

    override fun profit(other: Player, money: Money): Double = -other.profit(this, money)

    override fun stay(): Player = Dealer(name, state.stay())

    override fun draw(deck: Deck, drawStrategy: DrawStrategy): Player {
        var nowState = state
        drawStrategy.draw(deck).forEach { nowState = nowState.draw(it) }
        if (nowState is Hit && nowState.hands.isOverDealerDrawStandard()) {
            return Dealer(name, nowState.stay())
        }
        return Dealer(name, nowState)
    }

    companion object {
        const val DEALER_DRAW_STANDARD = 16

        private const val DEALER_NAME = "딜러"
        private val DEFAULT_NAME = Name(DEALER_NAME)
    }
}
