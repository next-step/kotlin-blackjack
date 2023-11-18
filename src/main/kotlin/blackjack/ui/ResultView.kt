package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.GameResult
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.User
import blackjack.domain.calculateResult

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

    fun printDealerHitMessage() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.")
    }

    fun printCardResult(players: Players) {
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

    fun printGameResult(players: Players) {
        println()
        println("## 최종 승패")
        val dealer = players.dealer
        val dealerResult = players.players
            .map { it.calculateResult(dealer) }
            .map { it.reverse() }
            .groupingBy { it }
            .eachCount()
        printDealerResult(dealerResult)
        printPlayersResult(players, dealer)
    }

    private fun printDealerResult(dealerResult: Map<GameResult, Int>) {
        print("딜러:")
        dealerResult[GameResult.WIN]?.let {
            print(" ${it}승")
        }
        dealerResult[GameResult.DRAW]?.let {
            print(" ${it}무")
        }
        dealerResult[GameResult.LOSE]?.let {
            print(" ${it}패")
        }
        println()
    }

    private fun printPlayersResult(players: Players, dealer: Dealer) {
        players.players
            .forEach {
                print("${it.name}: ")
                println(it.calculateResult(dealer).value)
            }
    }
}
