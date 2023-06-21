package blackjack

class Players(
    val players: List<Player>,
) : List<Player> by players {
    constructor(vararg players: Player) : this(players.map { it })
}
