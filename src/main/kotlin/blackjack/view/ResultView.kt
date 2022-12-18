package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.ResultStatus

object ResultView {
    fun printInitialStatus(game: BlackJackGame) {
        printPlayersName(game.dealer, game.players)
        printPlayersStatus(game.dealer, game.players)
    }

    fun printStatus(game: BlackJackGame) {
        println()
        println("${game.dealer.name} 카드: ${game.dealer.cards} - 결과: ${game.dealer.score}")
        game.players.forEach {
            println("${it.name} 카드: ${it.cards} - 결과: ${it.score}")
        }
    }

    private fun printPlayersName(dealer: Dealer, players: List<Player>) {
        val dealerName = dealer.name
        val names = players.joinToString(", ") { it.name }

        println("\n${"$dealerName ,$names"} 에게 2장의 카드를 나누었습니다.")
    }

    private fun printPlayersStatus(dealer: Dealer, players: List<Player>) {
        println("${dealer.name} 카드: ${dealer.cards}")
        players.forEach { printPlayerStatus(it) }
    }

    fun printPlayerStatus(player: Player) {
        println("${player.name} 카드: ${player.cards}")
    }

    fun printResults(game: BlackJackGame) {
        println("\n## 최종 승패")
        printDealerResults(game.dealer)
        game.playerResults.forEach {
            println("${it.player.name}: ${it.result.value}")
        }
    }

    private fun printDealerResults(dealer: Dealer) {
        val winCount = dealer.results.count { it == ResultStatus.WIN }
        val loseCount = dealer.results.count { it == ResultStatus.LOSE }
        val drawCount = dealer.results.count { it == ResultStatus.DRAW }

        println("${dealer.name}: $winCount 승 $loseCount 패 $drawCount 무")
    }
}
