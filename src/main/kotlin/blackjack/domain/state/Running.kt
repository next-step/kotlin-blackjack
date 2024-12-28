package blackjack.domain.state

import blackjack.domain.player.DealerState

abstract class Running : State {
    override fun profit(
        betAmount: Int,
        dealerState: DealerState,
    ): Double {
        throw IllegalArgumentException("게임이 종료되지 않았습니다.")
    }

    override fun isRunning(): Boolean {
        return true
    }
}
