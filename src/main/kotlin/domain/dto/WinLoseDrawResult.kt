package domain.dto

import domain.player.Player
import domain.player.PlayerGameResult

class WinLoseDrawResult(
    val playerResultMap: Map<PlayerGameResult, List<Player>>,
)
