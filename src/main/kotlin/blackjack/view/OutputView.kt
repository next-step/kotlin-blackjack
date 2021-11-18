package blackjack.view

import blackjack.model.Dealer
import blackjack.model.GameResult
import blackjack.model.Gamer
import blackjack.model.Gamers
import blackjack.view.res.getString

class OutputView {

    fun printFirstDraw(players: Gamers, drawCount: Int) {
        val names = players.players().map { it.name }.toList()
        println("딜러와 ${names.joinToString()}에게 ${drawCount}장의 카드를 나누었습니다.")
        players.toList().forEach { player -> printCards(player, true) }
        println()
    }

    fun printCards(gamer: Gamer) {
        if (gamer is Dealer) {
            println("\n딜러는 16이하라 한장의 카드를 더 받았습니다")
            return
        }
        printCards(gamer, true)
    }

    private fun printCards(gamer: Gamer, newline: Boolean) {
        val cardsDisplay = gamer.cards.toList().joinToString {
            "${it.denomination.symbol}${getString(it.suit)}"
        }
        print("${gamer.name}카드: $cardsDisplay")
        if (newline) println()
    }

    fun printResult(results: List<GameResult>) {
        println()
        results.forEach { result ->
            printCards(result.gamer, false)
            println(" - 결과: ${result.gamer.score}")
        }
        println("\n## 최종 승패")
        results.forEach { result -> println(getString(result)) }
    }
}
