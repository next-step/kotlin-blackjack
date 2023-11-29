package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Hand

class Player(
    val name: PlayerName,
    val getDesiredAction: (player: Player) -> Action,
    override val hand: Hand = Hand(),
) : CardPlayer {

    override fun hitOrStand(): Action {
        if (isBust) return Action.STAND
        return getDesiredAction(this)
    }
}
