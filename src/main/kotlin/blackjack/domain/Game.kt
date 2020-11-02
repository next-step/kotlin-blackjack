package blackjack.domain

import blackjack.view.REPLY_STAND

class Game(players: List<Player>, private val deck: Deck) {
    private val players = Players(players)
    private var turn = 0

    constructor(PlayerNames: String, deck: Deck = Deck()) : this(
        PlayerNames.split(PLAYER_NAMES_DELIMITER)
            .filterNot { it.isBlank() }
            .map { Player(it.trim()) },
        deck
    )

    init {
        this.players.setUpWithCards(deck)
    }

    fun giveChanceToDraw(reply: String): Player? {
        val currentPlayer = players.findPlayer(turn)
        val player = currentPlayer.chooseToDraw(reply, deck) ?: return null
        if (REPLY_STAND == reply || player.hasScoreMoreThanMax()) {
            turn++
        }
        return player
    }

    fun currentPlayer() = players.findPlayer(turn)

    fun isOver() = turn == players.size()

    fun result(): String {
        return (0 until players.size()).map { players.findPlayer(it) }
            .joinToString("\n") { "${it}카드: ${it.stateOfCards()} - 결과: ${it.amountOfScores()}" }
    }

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
        const val MAXIMUM_GAME_SCORE = 21
        const val DEFAULT_CARD_AMOUNT = 2
    }
}
