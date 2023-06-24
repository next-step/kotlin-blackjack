package blackjack.controller

import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.deck.RandomDeckShuffleStrategy
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
//        drawEachPlayer(game, playerList)
    }

    private fun firstDraw(game: Game, playerList: List<Player>) {
        game.firstDraw(playerList)
        resultView.printFirstDraw(playerList)
    }

    private fun drawEachPlayer(game: Game, playerList: List<Player>) {
        playerList.forEach {}
    }
}
