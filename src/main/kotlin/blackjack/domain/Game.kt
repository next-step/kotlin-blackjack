package blackjack.domain

import blackjack.view.REPLY_REJECT

class Game(players: List<Player>) {
    private var turn = 0
    private val _players = Players(players)
    val players: Players
        get() = _players

    constructor(PlayerNames: String) : this(
        PlayerNames.split(PLAYER_NAMES_DELIMITER)
            .filterNot { it.isBlank() }
            .map { Player(it.trim()) }
    )

    fun setUp(): Players {
        _players.setUpWithCards()
        return _players
    }

    fun giveChanceToDraw(reply: String): Player {
        val currentPlayer = _players.findPlayer(turn)
        val player = currentPlayer.getChanceToDraw(reply)
        if (REPLY_REJECT == reply || player.hasScoreMoreThanMax()) {
            turn++
        }
        return player
    }

    fun currentPlayer() = _players.findPlayer(turn)

    fun isOver() = turn == _players.size()

    fun result(): String {
        return (0 until _players.size()).map { _players.findPlayer(it) }
            .joinToString("\n") { "${it}카드: ${it.displayCards()} - 결과: ${it.sumOfScores()}" }
    }

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
        const val MAXIMUM_GAME_SCORE = 21
        const val DEFAULT_CARD_AMOUNT = 2
    }
}
