package blackjack.domain.player

class Players(private val players: List<Player>) {

    val size: Int = players.size

    operator fun Players.get(index: Int): Player {
        return players[index]
    }

    fun forEach(action: (Player) -> Unit) {
        players.forEach(action)
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

    fun getLosePlayers(dealerResult: Int): List<Player> {
        return players.filter { it.isBust() || dealerResult > it.calculateCard() }
    }
}
