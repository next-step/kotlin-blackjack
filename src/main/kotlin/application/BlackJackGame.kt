package application

import domain.player.Dealer
import domain.player.Participants
import domain.player.Playable
import domain.player.Player

class BlackJackGame(
    val dealer: Dealer = Dealer(),
    private val players: Participants
) {
    private lateinit var gameResult: GameResult

    fun init() {
        repeat(INIT_CARD_COUNT) {
            dealer.drawCard()
            players.receiveCard(dealer)
        }
    }

    fun receiveCard(playable: Playable) {
        dealer.giveCard(playable = playable)
    }

    fun allPlayers(): List<Player> {
        return this.players.allPlayers()
    }

    fun playsTurn(player: Player, queryReceiveCard: (Player) -> Boolean) {
        while (player.isAvailableReceive() && queryReceiveCard(player)) {
            receiveCard(player)
        }
    }

    private fun availableReceiveCard(playable: Playable): Boolean = playable.isAvailableReceive()

    fun tryDealerReceiveCard(): Boolean {
        val availableReceiveCard = availableReceiveCard(dealer)
        if (availableReceiveCard) {
            receiveCard(dealer)
        }
        return availableReceiveCard
    }

    fun complete(): GameResult {
        this.gameResult = GameResult()
        return this.gameResult
    }

    inner class GameResult {
        val resultPlayerBoards: List<ResultPlayerBoard>
        val resultDealerBoard: ResultDealerBoard

        init {
            var dealerWinCount = 0
            this.resultPlayerBoards = allPlayers().map {
                val isPlayerWin = dealer.isWin(it)
                if (!isPlayerWin) dealerWinCount++
                ResultPlayerBoard(name = it.name, isWin = isPlayerWin)
              }

            this.resultDealerBoard = ResultDealerBoard(allPlayers().size, dealerWinCount)
        }

    }

    data class ResultDealerBoard(
        val totalCount: Int,
        val winCount: Int
    )

    data class ResultPlayerBoard(
        val name: String,
        val isWin: Boolean
    )

    private companion object {
        const val INIT_CARD_COUNT = 2
    }
}
