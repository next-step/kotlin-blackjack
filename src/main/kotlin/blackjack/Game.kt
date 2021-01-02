package blackjack

class Game(players: List<Player>, private val deck: Deck) {
    private val players = Players(players as MutableList<Player>)
    private var turn = 0

    constructor(PlayNames: String, deck: Deck = Deck()) : this(
        PlayNames.split((",")).filterNot { it.isBlank() }.map { Player(it.trim()) },
        deck
    )

    init {
        for (i in 0 until DEFAULT_CARD_SIZE) {
            players.map { player: Player -> player.draw(deck.popDeck()!!) }
        }
    }

    fun allPlayers(): Players {
        return players
    }

    fun chanceDraw(reply: String) {

        val cunPlayer = players.findPlayer(turn)
        cunPlayer.chooseDraw(reply, deck.popDeck()!!) ?: turn++
    }

    fun amountOfCards(): Int {
        return players.totalAmountOfCards()
    }

    fun amountOfDeck(): Int? {
        return deck.size()
    }

    fun numberOfPlayers(): Int {
        return players.totalNumberOfPlayers()
    }

    fun currentlyPlayer(): Player {
        return players.findPlayer(turn)
    }

    fun isOver(): Boolean {
        return turn == players.totalNumberOfPlayers()
    }

    fun lastPlayer(): Player {
        return players.findPlayer(players.totalNumberOfPlayers())
    }

    fun result(): String {
        return (0 until players.totalNumberOfPlayers()).map { players.findPlayer(it) }
            .joinToString("\n") { "${it}카드: ${it.stateCards()} - 결과: ${it.playerScore()}" }
    }

    companion object {
        const val DEFAULT_CARD_SIZE = 2
    }
}
