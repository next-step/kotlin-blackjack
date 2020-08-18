package blackjack

import blackjack.model.BlackJackGame
import blackjack.model.Players
import blackjack.model.player.Player
import blackjack.view.InputView
import blackjack.view.ResultView

class BlackJackGameController(
    private val inputView: InputView = InputView,
    private val resultView: ResultView = ResultView,
    private val players: Players = Players(inputView.getNames()),
    private val blackJackGame: BlackJackGame = BlackJackGame(players)
) {

    fun startGame() {
        gameBatting()
        playsTurn()
        dealerTurn()
        showResult()
    }

    private fun gameBatting() {
        blackJackGame.battingGame()
        resultView.showPlayers(blackJackGame.getPlayerStatus())
    }

    private fun playsTurn() {
        blackJackGame.playsTurn { player ->
            repeatIsPlayHit(player)
        }
    }

    private fun dealerTurn() {
        blackJackGame.dealerTurn {
            resultView.showDealerMoreCard()
        }
    }

    private fun repeatIsPlayHit(player: Player) {
        while (player.canMoreCard() && inputView.getIsOneMore(player)) {
            blackJackGame.playHit(player)
            resultView.showPlayer(player)
        }
    }

    private fun showResult() {
        resultView.showResult(blackJackGame.getPlayerStatus())
        resultView.showGameResult(blackJackGame.getGameResult())
    }
}

fun main() {
    BlackJackGameController().startGame()
}
