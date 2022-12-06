package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleOutput


class BlackjackController {
    fun playGame() {
        val players = ConsoleInput().inputPlayers()
        val game = Game(players)
        ConsoleOutput().printInitialCards(game.players)

        scratchPlayers(game)
    }

    private fun scratchPlayers(game: Game) {
        game.players.list.forEach {
            scratch(it, game.dealer)
        }
        println()
    }

    private fun scratch(player: Player, dealer: Dealer) {
        while (!player.isBurst() && ConsoleInput().inputScratch(player)) {
            player.hit(dealer.divide())
            ConsoleOutput().printPlayerCards(player)
        }
    }
}

fun main() {
    BlackjackController().playGame()
}
