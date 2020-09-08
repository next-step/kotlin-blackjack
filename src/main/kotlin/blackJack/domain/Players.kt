package blackJack.domain

class Players(playerNames: List<String>) {
    val players = playerNames.map { Player(it) }

    fun makeMap(getWinOrLose: (player: Player) -> WinOrLose): Map<Player, Int> {
        return players.associateWith { it.getProfit(getWinOrLose(it)) }
    }

    fun forEach(function: (player: Player) -> Unit) {
        players.forEach { function(it) }
    }
}
