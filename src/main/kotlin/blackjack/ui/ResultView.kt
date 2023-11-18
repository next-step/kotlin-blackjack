package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.User

object ResultView {

    fun printInitPlayers(players: Players) {
        val playerNames = players.getNames()
            .joinToString { it }
        println()
        println("딜러와 ${playerNames}에게 2장의 카드를 나누었습니다.")

        printDealerNameAndCard(players.dealer)
        players.players
            .forEach { printPlayerNameAndCard(it) }
        println()
    }

    private fun printDealerNameAndCard(dealer: Dealer) {
        print("딜러 카드: ")
        printCard(dealer)
        println()
    }

    fun printPlayerNameAndCard(player: Player) {
        printName(player)
        printCard(player)
        println()
    }

    fun printResult(players: Players) {
        println()
        print("딜러 카드: ")
        printCard(players.dealer)
        printSum(players.dealer)
        println()
        for (player in players.players) {
            printName(player)
            printCard(player)
            printSum(player)
            println()
        }
    }

    private fun printName(player: Player) {
        print("${player.name}카드: ")
    }

    private fun printCard(user: User) {
        val playerCards = user.hand
            .cards
            .joinToString { "${it.num.symbol}${it.suit.value}" }
        print(playerCards)
    }

    private fun printSum(user: User) {
        print(" - 결과: ${user.hand.getSum()}")
    }
}
