package model

class Players(val players: List<Player>) {
    fun hit(dealer: Dealer) {
        players.forEach { player ->
            player.hit(dealer.hit())
        }
    }
}
