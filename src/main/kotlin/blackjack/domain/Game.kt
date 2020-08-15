package blackjack.domain

import blackjack.view.REPLY_STAND

class Game(players: List<Player>, private val dealer: Dealer) {
    private val _players = Players(players)
    val players: Players
        get() = _players
    private var turn = 0

    init {
        this._players.setUpWithCards(dealer)
    }

    constructor(PlayerNames: String, dealer: Dealer = Dealer()) : this(
        PlayerNames.split(PLAYER_NAMES_DELIMITER)
            .filterNot { it.isBlank() }
            .map { Player(it.trim()) },
        dealer
    )

    fun giveChanceToDraw(reply: String): Player? {
        val currentPlayer = _players.findPlayer(turn)
        val player = currentPlayer.chooseToDraw(reply, dealer) ?: return null
        if (REPLY_STAND == reply || player.hasScoreMoreThanMax()) {
            turn++
        }
        return player
    }

    fun currentPlayer() = _players.findPlayer(turn)

    fun isOver() = turn == _players.size()

    fun result(): String {
        return (0 until _players.size()).map { _players.findPlayer(it) }
            .joinToString("\n") { "${it}카드: ${it.stateOfCards()} - 결과: ${it.sumOfScores()}" }
    }

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
        const val MAXIMUM_GAME_SCORE = 21
        const val DEFAULT_CARD_AMOUNT = 2
    }
}
