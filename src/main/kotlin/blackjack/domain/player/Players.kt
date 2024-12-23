package blackjack.domain.player

import blackjack.domain.status.GameResult

class Players(private val players: List<Player>) {
    val size: Int = players.size

    operator fun get(index: Int): Player {
        return players[index]
    }

    fun forEach(action: (Player) -> Unit) {
        players.forEach(action)
    }

    fun <T> fold(initial: T, operation: (acc: T, Player) -> T): T {
        return players.fold(initial, operation)
    }

    fun startTurns(
        onTurnStarted: (Participant) -> String,
        onPrintResultCallback: (Participant) -> Unit,
    ) {
        players.forEach { player ->
            player.startTurn(onTurnStarted, onPrintResultCallback)
        }
    }

    fun initTurns(onPrintResultCallback: ((Participant) -> Unit)) {
        players.forEach { player ->
            player.initTurn(onPrintResultCallback)
        }
    }

    fun updateWinningStatus(result: GameResult) {
        players.forEach { player ->
            player.updateWinningStatus(result)
        }
    }
}
