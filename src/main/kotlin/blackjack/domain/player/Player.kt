package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Hand

class Player(
    val name: PlayerName,
    val desiredAction: (player: Player) -> Action,
    override val hand: Hand = Hand(),
) : CardPlayer {

    override fun hitOrStand(): Action {
        if (isGreaterOrEqualToMaxScore) return Action.STAND
        return desiredAction(this)
    }
}
