package blackjack.controller

import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleOutput

class BlackjackController {
    private val inputView = ConsoleInput
    private val outputView = ConsoleOutput

    fun playGame() {
        val game = Game(inputView.inputPlayers())
        val dealer = game.getDealer()
//        outputView.printInitialCards(game)
//
//        val players = scratchPlayers(game)
//        val dealer = scratchDealer(game)

        outputView.printGameResult(dealer, players)
    }

    private fun scratchDealer(game: Game): Player {
        return if (game.getDealer().canHit()) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            game.getDealer().hit(game.deck)
        } else {
            println("딜러는 17이상이라 카드를 받지 않았습니다.")
            game.getDealer()
        }
    }

    private fun scratchPlayers(game: Game): Players {
        val result = mutableListOf<Player>()
        game.players.list.map {
            var player = it
            while (player.canHit() && ConsoleInput.inputScratch(player)) {
                player = player.hit(game.deck)
                ConsoleOutput.printPlayerCards(player)
            }
            result.add(player)
        }

        println()
        return Players(result)
    }
}

fun main() {
    BlackjackController().playGame()
}
