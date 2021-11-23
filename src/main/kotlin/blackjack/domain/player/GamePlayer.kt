package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.player.state.PlayerState
import blackjack.domain.player.state.Ready
import blackjack.strategy.draw.DrawStrategy

data class GamePlayer(
    override val name: Name = DEFAULT_NAME,
    override val playerState: PlayerState = Ready(),
) : Player(name, playerState) {

    override fun draw(deck: Deck, drawStrategy: DrawStrategy): Player {
        TODO("Not yet implemented")
    }

    companion object {
        private const val DEALER_NAME = "딜러"
        val DEFAULT_NAME = Name(DEALER_NAME)
    }
}