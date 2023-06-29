package blackjack.view

import blackjack.domain.BlackJackGameBoard
import blackjack.domain.PlayerName

class BlackJackGameFlow {
    private lateinit var blackJackGameBoard: BlackJackGameBoard

    private val inputView = InputView()
    private val outputView = OutputView()

    fun start() {
        inputView.inputPlayerNames().also { readyGameBoardWith(it) }

        val players = blackJackGameBoard.getPlayers()

        outputView.outputInitialGameMessage(players.map { it.name })

        players.forEach { showCardsOf(it) }

        players.forEach {
            while (inputView.inputPlayerDrawCard(it.name)) {
                blackJackGameBoard.pickCard(it)
                showCardsOf(it)
            }
        }

        players.forEach { player ->
            outputView.outputPlayerScore(player.name, player.sCards(), player.sScore())
        }
    }

    private fun readyGameBoardWith(players: List<String>) {
        blackJackGameBoard = BlackJackGameBoard(players = players.toSet())
    }

    private fun showCardsOf(player: PlayerName) {
        val playerCards = blackJackGameBoard.getPlayerCards(player)
        outputView.outputPlayerCards(player.name, playerCards)
    }

    private fun PlayerName.sCards() = blackJackGameBoard.getPlayerCards(this)

    private fun PlayerName.sScore() = blackJackGameBoard.getPlayerScore(this).score
}
