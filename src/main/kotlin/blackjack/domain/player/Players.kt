package blackjack.domain.player

class Players(private val players: List<Player>) {

    val size: Int = players.size

    fun forEach(action: (Player) -> Unit) {
        players.forEach(action)
    }

    fun get(index: Int): Player {
        return players[index]
    }

    fun startTurns(
        onTurnStarted: (Participant) -> String,
        onPrintResultCallback: (Participant) -> Unit
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
}
