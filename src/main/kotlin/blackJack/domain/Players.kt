package blackJack.domain

class Players(names: List<String>) {
    val players = makePlayer(names)

    private fun makePlayer(names: List<String>): List<Player> = names.map { Player(it) }

    fun giveCardAll(dealer: Dealer) {
        forEach { dealer.giveCard(it) }
    }

    fun forEach(forEach: (player: Player) -> Unit) {
        players.forEach { forEach(it) }
    }

    fun joinToString(getString: (player: Player) -> String) {
        players.joinToString { getString(it) }
    }
}
