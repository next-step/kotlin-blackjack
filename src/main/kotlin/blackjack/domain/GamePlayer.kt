package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.state.Hit
import blackjack.domain.state.Initial
import blackjack.domain.state.State
import blackjack.domain.state.Stay
import blackjack.domain.strategy.draw.DrawStrategy
import blackjack.domain.strategy.hittable.GamePlayerHittableStrategy
import kotlin.math.roundToInt

data class GamePlayer(
    override val name: Name,
    override val state: State = Initial()
) : Player() {

    override fun draw(cardDeck: CardDeck, drawStrategy: DrawStrategy): Player {
        var drawState = state
        drawStrategy.draw(cardDeck).forEach {
            drawState = drawState.draw(it)
        }
        if (drawState is Hit && !drawState.score.canHit(GamePlayerHittableStrategy)) {
            GamePlayer(name, drawState.stay())
        }
        return GamePlayer(name, drawState)
    }

    override fun stay() = GamePlayer(name, Stay(cards))

    override fun profit(other: Player, money: Money): Int = state.profit(other.state, money).roundToInt()
}
