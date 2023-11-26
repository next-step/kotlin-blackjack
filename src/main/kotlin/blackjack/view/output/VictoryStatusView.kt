package blackjack.view.output

import blackjack.domain.result.game.VictoryStatues
import blackjack.domain.result.game.VictoryStatus

object VictoryStatusView {
    fun from(status: VictoryStatus): String = when (status) {
        VictoryStatus.WIN -> "승"
        VictoryStatus.PUSH -> "무"
        VictoryStatus.LOSS -> "패"
    }

    fun from(statuses: VictoryStatues): String =
        "${statuses.winCount}${from(VictoryStatus.WIN)} " +
            "${statuses.pushCount}${from(VictoryStatus.PUSH)} " +
            "${statuses.lossCount}${from(VictoryStatus.LOSS)}"
}
