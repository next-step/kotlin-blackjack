package blackjack.domain.player

import blackjack.domain.card.Deck
import blackjack.domain.player.state.MatchResult
import blackjack.domain.player.state.PlayerState
import blackjack.strategy.draw.DrawStrategy

sealed class Player(open val name: Name, open val playerState: PlayerState) {

    fun isFinished(): Boolean = playerState.isFinished()

    fun match(other: Player): MatchResult = playerState.match(other.playerState)

    abstract fun draw(deck: Deck, drawStrategy: DrawStrategy): Player
}
