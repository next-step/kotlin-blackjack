package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.player.name.Name
import blackjack.domain.player.state.PlayerState
import blackjack.domain.player.state.Ready
import blackjack.strategy.draw.DrawStrategy

data class GamePlayer(
    override val name: Name,
    override val playerState: PlayerState = Ready(),
) : Player(name, playerState) {

    override fun stay(): Player = GamePlayer(name, playerState.stay())

    override fun draw(deck: Deck, drawStrategy: DrawStrategy): Player {
        var nowState = playerState
        drawStrategy.draw(deck).forEach { nowState = nowState.draw(it) }
        return GamePlayer(name, nowState)
    }
}
