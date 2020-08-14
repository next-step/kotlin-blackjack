package blackJack.domain

class Players(val players: List<Player>) {
    fun giveCardAll(dealer: Dealer) {
        forEach { dealer.giveCard(it) }
    }

    fun forEach(f: (player: Player) -> Unit) {
        players.forEach { f(it) }
    }

    fun joinToString(f: (player: Player) -> String) {
        players.joinToString { f(it) }
    }
}
