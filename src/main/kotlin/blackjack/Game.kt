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
            players.forEach { player: Player -> player.draw(deck.popDeck()!!) }
        }
    }

    fun chanceDraw(reply: String) {
        if (reply == "n") {
            turn++
        }
        if (reply == "y") {
            val cunPlayer = players.findPlayer(turn)
            cunPlayer.chooseDraw(deck.popDeck()!!)
        } else {
            throw IllegalArgumentException("Please input correct value")
        }
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

    companion object {
        const val DEFAULT_CARD_SIZE = 2
    }
}
