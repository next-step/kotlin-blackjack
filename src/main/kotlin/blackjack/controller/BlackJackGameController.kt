package blackjack.controller

import blackjack.domain.game.BlackJackGame
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGameController {
    fun playBlackJack() {
        val players = InputView.getPlayerInput()
        val game = prepareBlackJack(players)
        startBlackJack(game, players)
        resultBlackJack(players)
    }

    private fun prepareBlackJack(players: Players): BlackJackGame {
        return BlackJackGame.create(players)
    }

    private fun startBlackJack(game: BlackJackGame, players: Players) {
        OutputView.printPlayersInitView(players)
        game.start()
        OutputView.printPlayersHandsView(players)
        game.bet(::getMoreCardOrNot, ::printPlayerCard)
    }

    private fun resultBlackJack(players: Players) = OutputView.printPlayersResultView(players)

    private fun getMoreCardOrNot(player: Player) = InputView.getCardOrNot(player.name)
    private fun printPlayerCard(player: Player) = OutputView.printPlayerHandsView(player)
}
