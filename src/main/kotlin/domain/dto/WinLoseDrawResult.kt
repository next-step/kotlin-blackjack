package domain.dto

import domain.player.Player
import domain.player.PlayerGameResult

class WinLoseDrawResult(
    val playerResult: Map<PlayerGameResult, List<Player>>,
)
