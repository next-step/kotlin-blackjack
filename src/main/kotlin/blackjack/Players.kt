package blackjack

class Players(
    private val players: List<Player>,
) : List<Player> by players {
    constructor(vararg players: Player) : this(players.map { it })

    companion object {
        fun init(playerNames: List<String>, deck: Deck) = Players(
            playerNames.map { Player(it, deck.draw(2)) }
        )
    }
}
