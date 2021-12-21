package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.state.Hit
import blackjack.domain.state.Initial
import blackjack.domain.state.Running
import blackjack.domain.state.State
import blackjack.domain.state.Stay
import blackjack.domain.strategy.draw.DrawStrategy
import blackjack.domain.strategy.hittable.DealerHittableStrategy

data class Dealer(
    override val name: Name = Name.from(DEALER_NAME),
    override val state: State = Initial()
) : Player() {

    override fun draw(cardDeck: CardDeck, drawStrategy: DrawStrategy): Dealer {
        var drawState = state
        drawStrategy
            .draw(cardDeck)
            .forEach {
                drawState = drawState.draw(it)
            }
        if (drawState is Hit && !(drawState as Running).canHit(DealerHittableStrategy)) {
            return Dealer(name, drawState.stay())
        }
        return Dealer(name, drawState)
    }

    fun canHit(): Boolean = state.score.canHit(DealerHittableStrategy)

    override fun stay(): Dealer = Dealer(name, Stay(cards))

    override fun profit(other: Player, money: Money): Int = -other.profit(this, money)

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
