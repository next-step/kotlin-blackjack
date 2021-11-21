package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Gamer
import blackjack.model.Gamers
import blackjack.model.Player
import blackjack.view.res.getString

class OutputView {

    fun printStart(gamers: Gamers) {
        val names = gamers.players().map { it.name }.toList()
        println("딜러와 ${names.joinToString()}에게 2장의 카드를 나누었습니다.")
        gamers.toList().forEach { player -> printCards(player, true) }
        println()
    }

    fun printRunning(gamer: Gamer) = when (gamer) {
        is Dealer -> println("\n딜러는 16이하라 한장의 카드를 더 받았습니다")
        is Player -> printCards(gamer, true)
    }

    private fun printCards(gamer: Gamer, newline: Boolean) {
        val cardsDisplay = gamer.cards.toList().joinToString {
            "${it.denomination.symbol}${getString(it.suit)}"
        }
        print("${gamer.name}카드: $cardsDisplay")
        if (newline) println()
    }

    fun printResult(gamers: Gamers) {
        val dealer = gamers.dealer() ?: return
        val dealerScore = dealer.score
        val players = gamers.players()

        // 딜러 카드
        println()
        printCards(dealer, false)
        println(" - 결과: $dealerScore")

        // 플레이어 카드
        players.forEach { player ->
            printCards(player, false)
            println(" - 결과: ${player.score}")
        }

        // 최종 수익
        println("## 최종 수익")
        println("딜러: ${players.sumOf { -it.profit() }}")
        players.forEach { player -> println("${player.name}: ${player.profit()}") }
    }
}
