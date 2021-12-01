package blackjack.domain.player

import blackjack.domain.bet.Money
import blackjack.domain.card.Deck
import blackjack.domain.player.name.Name
import blackjack.domain.player.state.State
import blackjack.strategy.draw.DrawStrategy

sealed class Player(open val name: Name, open val state: State) {

    fun isFinished(): Boolean = state.isFinished()

    abstract fun profit(other: Player, money: Money): Double

    abstract fun stay(): Player

    abstract fun draw(deck: Deck, drawStrategy: DrawStrategy): Player
}
