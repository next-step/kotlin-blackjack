package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.participant.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class Controller {
    fun playBlackJackGame() {
        val players = getPlayers()
        val game = BlackJackGame(players = players)
        startGame(game)
        playGame(game)
        printResult(game)
    }

    private fun getPlayers(): Players {
        val playerNames = InputView.getPlayerNames()
        return Players.of(playerNames)
    }

    private fun startGame(game: BlackJackGame) {
        game.start()
        OutputView.printStart(game.getParticipants())
    }

    private fun playGame(game: BlackJackGame) {
        game.playerPlay(
            isHit = { InputView.isHit(it.name) },
            afterDrawCard = { name, cards -> OutputView.printCards(name, cards) }
        )
        game.dealerPlay { OutputView.printDealerHit(it) }

        for (participant in game.getParticipants()) {
            OutputView.printPlayerResult(participant)
        }
    }

    private fun printResult(game: BlackJackGame) {
        val gameResults = game.getGameResult()
        for (result in gameResults.getPlayersResult()) {
            OutputView.printResult(result.player.name, result.ofPlayer())
        }
        OutputView.printResult(
            gameResults.dealer.name,
            gameResults.getDealerResult()
        )
    }
}

fun main() {
    val controller = Controller()
    controller.playBlackJackGame()
}
