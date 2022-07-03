package blackjack.domain.participant.state

import blackjack.domain.participant.GameResult

sealed class Running(cards: Cards) : AfterInit(cards) {

    override fun isFinished(): Boolean = false

    override fun judgementGameResult(otherScore: Score): GameResult {
        throw IllegalStateException("게임이 진행중인 상태에서는 승패 비교를 할 수 없습니다.")
    }
}
