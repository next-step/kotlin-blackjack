package view

import domain.player.Player
import domain.player.PlayerGameResult

class WinLoseDrawResult(
    val playerResultMap: Map<PlayerGameResult, List<Player>>,
)
