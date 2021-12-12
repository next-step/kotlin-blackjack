package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.state.Initial
import blackjack.domain.state.State
import blackjack.domain.state.Stay
import blackjack.domain.strategy.draw.DrawStrategy
import blackjack.domain.strategy.hittable.GamePlayerHittableStrategy

data class GamePlayer(
    override val name: Name,
    override val state: State = Initial()
) : Player() {

    override fun draw(cardDeck: CardDeck, drawStrategy: DrawStrategy): Player {
        var state = state
        drawStrategy.draw(cardDeck).forEach {
            state = state.draw(it)
        }
        return GamePlayer(name, state)
    }

    override fun stay() = GamePlayer(name, Stay(cards))

    override fun canHit(): Boolean = score.canHitScore(GamePlayerHittableStrategy)

    override fun copy(): GamePlayer = GamePlayer(name, state)
}
