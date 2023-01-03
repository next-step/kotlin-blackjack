package blackjack.view

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.PlayerResult
import blackjack.domain.Users

object ResultView {
    fun printInitialStatus(game: BlackJackGame) {
        printPlayersName(game.dealer, game.users)
        printPlayersStatus(game.dealer, game.users)
    }

    fun printStatus(game: BlackJackGame) {
        println()
        println("${game.dealer.name.value} 카드: ${game.dealer.cards} - 결과: ${game.dealer.score}")
        game.users.values.forEach {
            println("${it.name.value} 카드: ${it.cards} - 결과: ${it.score}")
        }
    }

    private fun printPlayersName(dealer: Dealer, players: Users) {
        val dealerName = dealer.name.value
        val names = players.values.joinToString(", ") { it.name.value }

        println("\n${"$dealerName ,$names"} 에게 2장의 카드를 나누었습니다.")
    }

    private fun printPlayersStatus(dealer: Dealer, players: Users) {
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
        println("\n## 최종 수익")
        printDealerProfit(dealer)
        printUsersProfit(playerResults)
    }
    private fun printDealerProfit(dealer: Dealer) {
        println("${dealer.name.value}: ${dealer.profit.value}")
    }

    private fun printUsersProfit(playerResults: List<PlayerResult>) {
        playerResults.forEach {
            println("${it.user.name.value}: ${it.profit.value}")
        }
    }
}
