package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.participant.Player
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
        val players = playerNames.map {
            val betAmount = InputView.getBetAmount(it)
            Player(name = it, betAmount = betAmount)
        }
        return Players(players)
    }

    private fun startGame(game: BlackJackGame) {
        game.start()
        OutputView.printStartGame(game.getParticipants())
    }

    private fun playGame(game: BlackJackGame) {
        game.playerPlay(
            isHit = { InputView.isHit(it.name) },
            afterDrawCard = { name, cards -> OutputView.printCards(name, cards) }
        )
        game.dealerPlay { OutputView.printDealerHit(it) }

        for (participant in game.getParticipants()) {
            OutputView.printParticipantScore(participant)
        }
    }

    private fun printResult(game: BlackJackGame) {
        val gameResults = game.getGameResult()
        for (result in gameResults.getMatchResults()) {
            OutputView.printProfit(result.player, result.ofPlayer().earningAmount)
        }
        OutputView.printProfit(
            gameResults.dealer,
            gameResults.getDealerEarningAmount()
        )
    }
}

fun main() {
    val controller = Controller()
    controller.playBlackJackGame()
}
