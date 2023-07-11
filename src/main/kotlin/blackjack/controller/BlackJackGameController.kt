package blackjack.controller

import blackjack.domain.game.BlackJackGame
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.OutputView

class BlackJackGameController {
    fun playBlackJack() {
        val players = getPlayers()
        val game = BlackJackGame.create()
        printPlayersInitView(players)
        game.prepare(players)
        printPlayersHandsView(players)
        game.race(players, ::getMoreCardOrNot, ::printPlayerCard)
        printResult(players)
    }

    private fun getPlayers(): Players = InputView.getPlayerInput()
    private fun printPlayersHandsView(players: Players) = OutputView.printPlayersHandsView(players)
    private fun printPlayersInitView(players: Players) = OutputView.printPlayersInitView(players)
    private fun getMoreCardOrNot(player: Player) = InputView.getCardOrNot(player.name)
    private fun printPlayerCard(player: Player) = OutputView.printPlayerHandsView(player)
    private fun printResult(players: Players) = OutputView.printPlayersResultView(players)
}
