package blackjack

import blackjack.domain.game.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.view.InputView
import blackjack.view.ResultView

object Application {

    @JvmStatic
    fun main(args: Array<String>) {
        val players: Players = processInput { requestPlayers() }

        // 카드 2장 분배
        val dealer = Dealer()
        dealer.initPlayersHand(players)
        ResultView.printGiveCard(players)
        ResultView.printPlayers(players)

        // 카드 추가 분배
        for (player in players.list) {
            processMoreAction(player, dealer)
        }

        ResultView.printPlayers(players)
    }

    private fun <T> processInput(requestInput: () -> T?): T {
        var result: T?
        do {
            result = requestInput()
        } while (result == null)
        return result
    }

    private fun requestPlayers() = try {
        Players(InputView.readPlayers())
    } catch (e: IllegalArgumentException) {
        ResultView.printInvalidName()
        null
    }

    private fun requestModeCard(player: Player) = InputView.readMoreCard(player) == InputView.YES

    private fun processMoreAction(player: Player, dealer: Dealer) {
        while (player.hasFreeSpace() && requestModeCard(player)) {
            val handStatus = dealer.divideCardTo(player)
            ResultView.printPlayer(player, handStatus)
        }
    }
}
