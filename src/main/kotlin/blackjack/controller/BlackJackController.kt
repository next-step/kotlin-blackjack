package blackjack.controller

import blackjack.domain.Game
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
        val game = Game(RandomDeckShuffleStrategy())

        firstDraw(game, playerList)

        try {
            askPlayersWantToDrawCard(game, playerList)
        } catch (e: PlayerLoseException) {
            println()
        }

        printGameResult(playerList)
    }

    private fun firstDraw(game: Game, playerList: List<Player>) {
        game.firstDraw(playerList)
        resultView.printFirstDraw(playerList)
    }

    private fun askPlayersWantToDrawCard(game: Game, playerList: List<Player>) {
        playerList.forEach { askPlayerWantToDrawCard(game, it) }
        println()
    }

    private fun askPlayerWantToDrawCard(game: Game, player: Player) {
        while (continueDrawingCards(player)) {
            drawPlayer(game, player)
            game.checkPlayerIsLose(player)
        }
        resultView.printPlayerCardList(player)
        println()
    }

    private fun continueDrawingCards(player: Player): Boolean {
        resultView.printPlayersWantToDrawCard(player)
        return inputView.askPlayersWantToDrawCard()
    }

    private fun drawPlayer(game: Game, player: Player) {
        game.onePlayerDraw(player)
        resultView.printPlayerCardList(player)
        println()
    }

    private fun printGameResult(playerList: List<Player>) {
        resultView.printGameResult(playerList)
    }
}
