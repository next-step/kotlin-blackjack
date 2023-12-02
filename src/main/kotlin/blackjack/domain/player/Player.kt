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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}
