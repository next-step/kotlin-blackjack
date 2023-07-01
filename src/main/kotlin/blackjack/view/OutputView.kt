package blackjack.view

import blackjack.Player
import blackjack.dto.PlayerGameResult

interface OutputView {
    fun showInitialStatus(players: List<Player>)
    fun showCurrentStatusOf(player: Player)
    fun showGameResult(playerGameResults: List<PlayerGameResult>)
}
