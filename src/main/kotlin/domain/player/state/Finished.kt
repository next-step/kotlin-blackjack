package domain.player.state

import domain.card.PlayingCard
import exception.IllegalDrawException
import exception.IllegalStayException

sealed class Finished(other: PlayerState) : PlayerState(other.cards) {
    override fun isFinished() = true
    override fun stay(): PlayerState {
        throw IllegalStayException("차례가 끝난 상태 에서는 stay 할 수 없습니다.")
    }

    override fun draw(playingCard: PlayingCard): PlayerState {
        throw IllegalDrawException("차례가 끝난 상태 에서는 draw 할 수 없습니다.")
    }
}
