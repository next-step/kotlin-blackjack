package blackjack

class Game(players: List<Player>) {
    private val players = Players(players as MutableList<Player>)
    private val turn = 0

    constructor(PlayNames: String) : this(
        PlayNames.split((",")).filterNot { it.isBlank() }.map { Player(it.trim()) }
    )
}
