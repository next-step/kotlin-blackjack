package blackjack.domain

class Game(players: List<Player>?, private val deck: Deck) {
    private val players = Players(players as MutableList<Player>)
    private var turn = 0

    constructor(PlayNames: String?, deck: Deck = Deck()) : this(
        PlayNames?.split((","))?.filterNot { it.isBlank() }?.map { Player(it.trim()) },
        deck
    )

    init {
        for (i in 0 until DEFAULT_CARD_SIZE) {
            players?.map { player: Player -> player.hit(deck.popDeck()!!) }
        }
    }

    fun allPlayers(): Players {
        return players
    }

    fun chanceDraw(reply: String): Player? {

        val cunPlayer = players.findPlayer(turn)
        val player = deck.popDeck()?.let { cunPlayer.chooseDraw(reply, it) } ?: return null
        if (reply == "n" || player.hasMoreThanMax()) {
            turn++
        }
        return cunPlayer
    }

    fun amountOfDeck(): Int? {
        return deck.size()
    }
    /*
    fun numberOfPlayers(): Int {
        return players.totalNumberOfPlayers()
    }
*/
    fun currentlyPlayer(): Player {
        return players.findPlayer(turn)
    }

    fun isOver(): Boolean {
        return turn == players.totalNumberOfPlayers()
    }

    /*
    fun lastPlayer(): Player {
        return players.findPlayer(players.totalNumberOfPlayers() - 1)
    }
*/
    fun result(): String {
        return (0 until players.totalNumberOfPlayers()).map { players.findPlayer(it) }
            .joinToString("\n") { "${it}카드: ${it.stateCards()} - 결과: ${it.playerScore()}" }
    }

    companion object {
        const val DEFAULT_CARD_SIZE = 2
    }
}
