package blackjack.vo

import blackjack.domain.Player
import blackjack.domain.Result

data class GameResultVO(
    val dealerWinMap: Map<Result, Int> = mutableMapOf(),
    val playersWinMap: Map<Player, Result> = mutableMapOf()
)
