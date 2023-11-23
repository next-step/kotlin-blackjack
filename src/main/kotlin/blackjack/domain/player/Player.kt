package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.card.Hand

class Player(
    val name: PlayerName,
    val actionOf: (player: Player) -> Action,
    override val hand: Hand = Hand(),
) : CardHolder {
    override fun hitOrStand(): Action {
        if (hand.score.isOverMaxScore) return Action.STAND
        return actionOf(this)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Player

        if (name != other.name) return false
        if (hand != other.hand) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result = 31 * result + hand.hashCode()
        return result
    }
}
