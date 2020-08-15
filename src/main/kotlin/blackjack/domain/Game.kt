package blackjack.domain

import blackjack.view.REPLY_STAND

class Game(players: List<Player>, val dealer: Dealer) {
    private val _players = Players(players)
    val players: Players
        get() = _players
    private var turn = 0

    init {
        dealer.setUpWithCards(dealer)
        _players.setUpWithCards(dealer)
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

        canGoToNextTurn(reply, player)
        return player
    }

    private fun canGoToNextTurn(reply: String, player: Player) {
        if (REPLY_STAND == reply ||
            player.hasMoreScoreThanMax(player.totalScore())
        ) {
            turn++
        }
    }

    fun playOfDealer(): Dealer? {
        return dealer.drawIf(dealer) { dealer.hasLessScoreThan17() } as Dealer?
    }

    fun getResult(): Pair<Dealer, Players> {
        val winCountOfPlayers = players.compareWith(dealer)
        dealer.checkWin(players.size(), winCountOfPlayers)
        return Pair(dealer, players)
    }

    fun currentPlayer() = _players.findPlayer(turn)

    fun isOver() = turn == _players.size()

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
        const val MAXIMUM_GAME_SCORE = 21
        const val SCORE_DEALER_SHOULD_TAKE_A_CARD = 17
        const val DEFAULT_CARD_AMOUNT = 2
    }
}
