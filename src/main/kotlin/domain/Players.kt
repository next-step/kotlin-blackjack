package domain

class Players(players: List<Player>) {

    private val players: ArrayDeque<Player> = ArrayDeque(players)

    fun currentPlayer(): Player {
        val currentPlayer = players.removeFirst()
        players.addLast(currentPlayer)
        return currentPlayer
    }

    fun quitGame(player: Player) {
        players.removeIf { it == player }
    }
}
