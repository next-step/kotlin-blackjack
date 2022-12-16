package blackjack.controller

import blackjack.domain.Deck
import blackjack.domain.Game
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.PlayersResult
import blackjack.view.ConsoleInput
import blackjack.view.ConsoleOutput

class BlackjackController {
    private val inputView = ConsoleInput
    private val outputView = ConsoleOutput

    fun playGame() {
        val game = Game(inputView.inputPlayers())
        val dealer = game.getDealer()
        val players = game.initialCard()
        outputView.printInitialCards(dealer, players)

        val playersResult = scratchPlayers(players, game.deck)
        val dealerResult = scratchDealer(dealer, game.deck)

        val gameResult = PlayersResult(dealerResult, playersResult)

        outputView.printResultCards(gameResult)
        outputView.printGameResult(gameResult)
    }
    
    private fun scratchDealer(dealer: Player, deck: Deck): Player {
        return if (dealer.canHit()) {
            println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
            dealer.hit(deck)
        } else {
            println("딜러는 17이상이라 카드를 받지 않았습니다.\n")
            dealer
        }
    }

    private fun scratchPlayers(players: Players, deck: Deck): Players {
        val result = mutableListOf<Player>()
        players.list.map {
            var player = it
            while (player.canHit() && ConsoleInput.inputScratch(player)) {
                player = player.hit(deck)
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
