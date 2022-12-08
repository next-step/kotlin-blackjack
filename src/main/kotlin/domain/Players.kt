package domain

class Players(players: List<Player>) {

    val players: ArrayDeque<Player> = ArrayDeque(players)

    fun currentPlayer(): Player {
        val currentPlayer = players.removeFirst()
        if (!currentPlayer.canDrawCard()) {
            quitGame(currentPlayer)
            return currentPlayer
        }
        players.addLast(currentPlayer)
        return currentPlayer
    }

    fun quitGame(player: Player) {
        players.removeIf { it == player }
    }

    fun isNotEmpty() = players.isNotEmpty()
}
