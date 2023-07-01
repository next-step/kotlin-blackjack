package blackjack.controller

import blackjack.domain.BlackJackGame
import blackjack.domain.BlackJackGamer
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
        val dealer = Dealer()
        val playerList = Player.generatePlayers(playerNameList)
        val blackJackGame = BlackJackGame(RandomDeckShuffleStrategy())

        val gamerList = makeGamerList(playerList, dealer)

        firstDraw(blackJackGame, gamerList)
        printFirstDraw(playerList, dealer)

        askPlayersWantToDrawCard(blackJackGame, playerList)

        checkDealerCards(blackJackGame, dealer)

        printGameResult(playerList, dealer)
    }

    private fun makeGamerList(playerList: List<Player>, dealer: Dealer): List<BlackJackGamer> {
        val gamerList = mutableListOf<BlackJackGamer>(dealer)
        playerList.forEach {
            gamerList.add(it)
        }
        return gamerList.toList()
    }

    private fun firstDraw(blackJackGame: BlackJackGame, gamerList: List<BlackJackGamer>) {
        blackJackGame.firstDraw(gamerList)
    }

    private fun printFirstDraw(playerList: List<Player>, dealer: Dealer) {
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
            if (!blackJackGame.checkBlackJackGamerIsDraw(player)) {
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
        blackJackGame.oneGamerDraw(player)
        resultView.printPlayerCardList(player)
        resultView.printNextLine()
    }

    private fun checkDealerCards(blackJackGame: BlackJackGame, dealer: Dealer) {
        if (!blackJackGame.checkBlackJackGamerIsDraw(dealer)) {
            resultView.printDealerIsDraw()
            blackJackGame.oneGamerDraw(dealer)
        }
    }

    private fun printGameResult(playerList: List<Player>, dealer: Dealer) {
        resultView.printGameResult(playerList, dealer)
    }
}
