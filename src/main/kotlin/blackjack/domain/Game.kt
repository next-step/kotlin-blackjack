package blackjack.domain

import blackjack.view.REPLY_STAND

class Game(val players: Players, val dealer: Dealer) {
    private var turn = 0

    init {
        dealer.setUpWithPlayers(players)
    }

    constructor(PlayerNames: String, dealer: Dealer = Dealer()) : this(
        Players(
            PlayerNames.split(PLAYER_NAMES_DELIMITER)
                .filterNot { it.isBlank() }
                .map { Player(it.trim()) }
        ),
        dealer
    )

    fun giveChanceToDraw(reply: String): Player? {
        val currentPlayer = players.findPlayer(turn)
        val player = currentPlayer.chooseToDraw(reply, dealer.pickCard()) ?: return null

        canGoToNextTurn(reply, player)
        return player
    }

    private fun canGoToNextTurn(reply: String, player: Player) {
        if (REPLY_STAND == reply ||
            player.hasScoreMoreThanMax(player.totalScore())
        ) {
            turn++
        }
    }

    fun playOfDealer(): Dealer? {
        return dealer.drawCardIf(dealer.pickCard()) { dealer.hasLessScoreThan17(dealer.totalScore()) } as Dealer?
    }

    fun getResult(): Pair<Dealer, Players> {
        val winCountOfPlayers = players.compareWith(dealer)
        dealer.checkWin(players.size(), winCountOfPlayers)
        return Pair(dealer, players)
    }

    fun currentPlayer() = players.findPlayer(turn)

    fun isOver() = turn == players.size()

    companion object {
        private const val PLAYER_NAMES_DELIMITER = ","
        const val MAXIMUM_GAME_SCORE = 21
        const val MAXIMUM_SCORE_FOR_DEALER_DRAWING = 17
        const val DEFAULT_CARD_AMOUNT = 2
    }
}
