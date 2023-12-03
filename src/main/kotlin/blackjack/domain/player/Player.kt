package blackjack.domain.player

import blackjack.domain.Action
import blackjack.domain.Dealer
import blackjack.domain.card.Hand
import blackjack.domain.result.game.VictoryStatus

class Player(
    val name: PlayerName,
    val desiredAction: (player: Player) -> Action,
    override val hand: Hand = Hand(),
) : CardPlayer {

    fun judgeVictory(dealer: Dealer): VictoryStatus =
        when {
            score.isBust -> VictoryStatus.LOSS
            score isGreaterGameScoreThan dealer.score -> VictoryStatus.WIN
            score isSameGameScoreTo dealer.score -> VictoryStatus.PUSH
            else -> VictoryStatus.LOSS
        }

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
