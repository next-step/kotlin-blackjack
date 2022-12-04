package model

class Players(
    val players: List<Player>
) {
    constructor(playerNames: String) : this(playerNames.split(",").map { Player(it) })

    fun hit(dealer: Dealer) = players.forEach { player -> player.hit(dealer.pick()) }
}
