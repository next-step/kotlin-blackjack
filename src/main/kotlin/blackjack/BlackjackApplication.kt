package blackjack

import blackjack.model.Game
import blackjack.model.player.Players
import blackjack.view.InputView
import blackjack.view.MoreCardMark
import blackjack.view.ResultView

object BlackjackApplication {
    fun run(inputView: InputView, resultView: ResultView) {
        val game = startGame(inputView)
        resultView.printPlayersCardStatus(game.players)

        playGame(game, inputView, resultView)
        resultView.printCardGameResult(game.players)
    }

    private fun startGame(inputView: InputView): Game {
        inputView.printPlayerNamesInputMessage()

        val players = Players(inputView.inputPlayerNames())
        val game = Game(players = players)

        game.start()

        return game
    }

    private fun playGame(game: Game, inputView: InputView, resultView: ResultView) {
        while (game.isNotEnd()) {
            inputView.printNeedMoreCardAskMessage(game.turnPlayerName)

            val needMoreCardMark = inputView.inputWhetherNeedMoreCard()
            if (MoreCardMark.needMoreCard(needMoreCardMark)) {
                game.provideCardToTurnPlayer()
                resultView.printPlayerCardStatus(game.turnPlayer)
                continue
            }

            game.changeTurn()
        }
    }
}
