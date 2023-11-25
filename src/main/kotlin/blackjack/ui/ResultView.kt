package blackjack.ui

import blackjack.domain.Dealer
import blackjack.domain.GameResult
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

    fun printGameResult(dealerResult: Map<GameResult, Int>, playersResult: Map<String, GameResult>) {
        println()
        println("## 최종 승패")
        printDealerResult(dealerResult)
        printPlayersResult(playersResult)
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

    private fun printPlayersResult(players: Map<String, GameResult>) {
        players.forEach {
            print("${it.key}: ")
            when (it.value) {
                GameResult.WIN -> println("승")
                GameResult.DRAW -> println("무")
                GameResult.LOSE -> println("패")
            }
        }
    }

    fun printProfit(dealerProfit: Int, playersProfit: Map<String, Int>) {
        println()
        println("## 최종 수익")
        println("딜러: $dealerProfit")
        playersProfit.forEach {
            println("${it.key}: ${it.value}")
        }
    }
}
