package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.state.Hit
import blackjack.domain.state.Initial
import blackjack.domain.state.State
import blackjack.domain.state.Stay
import blackjack.domain.strategy.draw.DrawStrategy
import blackjack.domain.strategy.hittable.DealerHittableStrategy

data class Dealer(
    override val name: Name = Name.from(DEALER_NAME),
    override val state: State = Initial()
) : Player() {

    override fun draw(cardDeck: CardDeck, drawStrategy: DrawStrategy): Dealer {
        if (state is Hit && !canHit()) {
            return Dealer(name, state.stay())
        }
        var state = state
        drawStrategy.draw(cardDeck).forEach {
            state = state.draw(it)
        }
        return Dealer(name, state)
    }

    override fun stay(): Dealer = Dealer(name, Stay(cards))

    override fun copy(): Dealer = Dealer(name, state)

    override fun canHit(): Boolean = score.canHitScore(DealerHittableStrategy)

    companion object {
        private const val DEALER_NAME = "딜러"
    }
}
