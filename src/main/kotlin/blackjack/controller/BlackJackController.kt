package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
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
        val blackJackGame = BlackJackGame(RandomDeckShuffleStrategy())
        val dealer = Dealer()

        firstDraw(blackJackGame, playerList, dealer)

        askPlayersWantToDrawCard(blackJackGame, playerList)

        printGameResult(playerList)
    }

    private fun firstDraw(blackJackGame: BlackJackGame, playerList: List<Player>, dealer: Dealer) {
        blackJackGame.firstDraw(playerList, dealer)
        resultView.printFirstDraw(playerList, dealer)
    }

    private fun askPlayersWantToDrawCard(blackJackGame: BlackJackGame, playerList: List<Player>) {
        playerList.forEach {
            askPlayerWantToDrawCard(blackJackGame, it)
        }
        resultView.printNextLine()
    }

    private fun askPlayerWantToDrawCard(blackJackGame: BlackJackGame, player: Player): Player? {
        while (continueDrawingCards(player)) {
            drawPlayer(blackJackGame, player)
            if (!blackJackGame.checkPlayerIsLose(player)) {
                return null
            }
        }
        resultView.printPlayerCardList(player)
        return player
    }

    private fun continueDrawingCards(player: Player): Boolean {
        resultView.printPlayersWantToDrawCard(player)
        return inputView.askPlayersWantToDrawCard()
    }

    private fun drawPlayer(blackJackGame: BlackJackGame, player: Player) {
        blackJackGame.onePlayerDraw(player)
        resultView.printPlayerCardList(player)
        resultView.printNextLine()
    }

    private fun printGameResult(playerList: List<Player>) {
        resultView.printGameResult(playerList)
    }
}
