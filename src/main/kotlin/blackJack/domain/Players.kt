package blackJack.domain

class Players(playerNames: List<String>) {
    val players = playerNames.map { Player(it) }

    fun makeMap(f: () -> Unit): Map<Player, Int> {
        return mapOf(Player("test") to 0)
    }
}
