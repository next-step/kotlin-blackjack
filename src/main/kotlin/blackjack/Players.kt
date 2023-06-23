package blackjack

class Players(
    private val players: List<Player>,
) : List<Player> by players {
    constructor(vararg players: Player) : this(players.map { it })

    companion object {
        private const val INITIAL_DRAW_COUNT = 2

        fun init(playerNames: List<String>, deck: Deck) = Players(
            playerNames.map { Player(it, deck.draw(INITIAL_DRAW_COUNT)) }
        )
    }
}
