package blackjack

import blackjack.model.Game
import blackjack.model.player.Dealer
import blackjack.model.player.Player
import blackjack.model.player.PlayerGameResults
import blackjack.model.player.Players
import blackjack.view.InputView
import blackjack.view.MoreCardMark
import blackjack.view.ResultView

object BlackjackApplication {
    fun run(inputView: InputView, resultView: ResultView) {
        val game = startGame(inputView)
        resultView.printPlayersCardStatus(game.players)

        playGame(game, inputView, resultView)
        resultView.printCardGameResult(PlayerGameResults.from(game.players))
    }

    private fun startGame(inputView: InputView): Game {
        inputView.printPlayerNamesInputMessage()

        val players = Players(inputView.inputPlayerNames().plus(Dealer()))
        val game = Game(players = players)

        game.start()

        return game
    }

    private fun playGame(game: Game, inputView: InputView, resultView: ResultView) {
        while (game.isNotEnd()) {

            val needMoreCardMark = printAndInputWhetherNeedMoreCard(inputView, resultView, game.turnPlayer)
            if (MoreCardMark.needMoreCard(needMoreCardMark)) {
                game.provideCardToTurnPlayer()
                resultView.printPlayerCardStatus(game.turnPlayer)
                continue
            }

            game.changeTurn()
        }
    }

    private fun printAndInputWhetherNeedMoreCard(
        inputView: InputView,
        resultView: ResultView,
        turnPlayer: Player
    ): String {
        if (turnPlayer.isDealer && turnPlayer.needMoreCard) {
            resultView.printDealerReceiveMoreCardMessage(turnPlayer.name)
            return MoreCardMark.YES.mark
        }

        inputView.printNeedMoreCardAskMessage(turnPlayer.name)
        return inputView.inputWhetherNeedMoreCard()
    }
}
