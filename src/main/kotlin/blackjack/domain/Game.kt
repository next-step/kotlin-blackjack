package blackjack.domain

class Game(playerNames: String, whetherHit: (Player) -> Boolean) {
    private val cardDeck: CardDeck = CardDeck()

    private val _players: Set<Player>
    val players: Set<Player>
        get() = _players

    init {
        require(playerNames.isNotBlank()) { "playerNames is not blank." }

        _players = playerNames.split(NAME_DELIMETER)
            .map { Player(it, whetherHit) }
            .toSet()
    }

    private fun drawCards() {
        _players.forEach {
            it.receiveCard(cardDeck.draw())
        }
    }

    fun start(printStart: (Set<Player>) -> Unit): Game {
        repeat(FIRST_DRAW_COUNT) {
            drawCards()
        }

        printStart(players)
        return this
    }

    fun hits(printPlayer: (Player) -> Unit): Game {
        _players.forEach {
            it.hit({ cardDeck.draw() }, printPlayer)
        }
        return this
    }

    companion object {
        const val WINNER_SCORE = 21
        const val ACE_ADDITIONAL_SCORE = 10
        const val NAME_DELIMETER = ","
        const val FIRST_DRAW_COUNT = 2
    }
}
