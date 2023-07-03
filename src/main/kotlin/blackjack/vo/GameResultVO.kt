package blackjack.vo

import blackjack.domain.FightResult
import blackjack.domain.Player

data class GameResultVO(
    val dealerWinMap: Map<FightResult, Int>,
    val playersWinMap: Map<Player, FightResult>,
)
