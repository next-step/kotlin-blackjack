package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Player
import blackjack.domain.deck.RandomDeckShuffleStrategy
import blackjack.exception.PlayerLoseException
import blackjack.ui.InputView
import blackjack.ui.ResultView

class BlackJackController(
    val inputView: InputView,
    val resultView: ResultView
) {

    fun start() {
        val playerNameList = inputView.getPlayerNames()
        val playerList = Player.generatePlayers(playerNameList)
        val blackJackGame = BlackJackGame(RandomDeckShuffleStrategy())

        firstDraw(blackJackGame, playerList)

        askPlayersWantToDrawCard(blackJackGame, playerList)

        printGameResult(playerList)
    }

    private fun firstDraw(blackJackGame: BlackJackGame, playerList: List<Player>) {
        blackJackGame.firstDraw(playerList)
        resultView.printFirstDraw(playerList)
    }

    private fun askPlayersWantToDrawCard(blackJackGame: BlackJackGame, playerList: List<Player>) {
        playerList.forEach {
            checkPlayerIsLoseByAskingPlayerWantToDraw(blackJackGame, it)
        }
        println()
    }

    private fun checkPlayerIsLoseByAskingPlayerWantToDraw(blackJackGame: BlackJackGame, it: Player) {
        try {
            askPlayerWantToDrawCard(blackJackGame, it)
        } catch (e: PlayerLoseException) {
            println()
        }
    }

    private fun askPlayerWantToDrawCard(blackJackGame: BlackJackGame, player: Player) {
        while (continueDrawingCards(player)) {
            drawPlayer(blackJackGame, player)
            blackJackGame.checkPlayerIsLose(player)
        }
        resultView.printPlayerCardList(player)
        println()
    }

    private fun continueDrawingCards(player: Player): Boolean {
        resultView.printPlayersWantToDrawCard(player)
        return inputView.askPlayersWantToDrawCard()
    }

    private fun drawPlayer(blackJackGame: BlackJackGame, player: Player) {
        blackJackGame.onePlayerDraw(player)
        resultView.printPlayerCardList(player)
        println()
    }

    private fun printGameResult(playerList: List<Player>) {
        resultView.printGameResult(playerList)
    }
}
