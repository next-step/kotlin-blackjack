package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.PlayerResult
import blackjack.domain.Players
import blackjack.domain.ResultStatus

object ResultView {
    fun printInitialStatus(game: BlackJackGame) {
        printPlayersName(game.dealer, game.players)
        printPlayersStatus(game.dealer, game.players)
    }

    fun printStatus(game: BlackJackGame) {
        println()
        println("${game.dealer.name.value} 카드: ${game.dealer.cards} - 결과: ${game.dealer.score}")
        game.players.values.forEach {
            println("${it.name.value} 카드: ${it.cards} - 결과: ${it.score}")
        }
    }

    private fun printPlayersName(dealer: Dealer, players: Players) {
        val dealerName = dealer.name.value
        val names = players.values.joinToString(", ") { it.name.value }

        println("\n${"$dealerName ,$names"} 에게 2장의 카드를 나누었습니다.")
    }

    private fun printPlayersStatus(dealer: Dealer, players: Players) {
        println("${dealer.name.value} 카드: ${dealer.cards.values[0]}")
        players.values.forEach { printPlayerStatus(it) }
    }

    fun printPlayerStatus(player: Player) {
        println("${player.name.value} 카드: ${player.cards}")
    }

    fun printDealerHitOrStay(isHit: Boolean) {
        when (isHit) {
            true -> println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
            false -> println("딜러는 17이상이라 카드를 받지 않았습니다.")
        }
    }

    fun printResults(playerResults: List<PlayerResult>, dealer: Dealer) {
        println("\n## 최종 승패")
        printDealerResults(dealer)
        playerResults.forEach {
            println("${it.player.name.value}: ${it.result.value}")
        }
    }

    private fun printDealerResults(dealer: Dealer) {
        val dealerResults = dealer.results.groupingBy { it }.eachCount()
        val results = ResultStatus.values()
            .map { it to dealerResults[it] }
            .joinToString(" ") { "${if (it.second == null) 0 else it.second} ${it.first.value}" }

        println("${dealer.name.value}: $results")
    }
}
