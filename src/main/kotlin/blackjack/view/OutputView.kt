package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Gamer
import blackjack.model.Gamers
import blackjack.model.Result
import blackjack.model.lose
import blackjack.model.push
import blackjack.model.win
import blackjack.view.res.getString

class OutputView {

    fun printFirstDraw(gamers: Gamers, drawCount: Int) {
        val names = gamers.players().map { it.name }.toList()
        println("딜러와 ${names.joinToString()}에게 ${drawCount}장의 카드를 나누었습니다.")
        gamers.toList().forEach { player -> printCards(player, true) }
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

    fun printResult(gamers: Gamers) {
        val dealer = gamers.dealer() ?: return
        val dealerScore = dealer.score
        val players = gamers.players()
        val playerResults = players.map { player -> player.result(dealerScore) }

        // 딜러 카드
        println()
        printCards(dealer, false)
        println(" - 결과: $dealerScore")

        // 플레이어 카드
        players.forEach { player ->
            printCards(player, false)
            println(" - 결과: ${player.score}")
        }

        // 최종 결과
        println("\n## 최종 승패")
        println(getDealerResult(playerResults))
        players.zip(playerResults) { player, result -> println("${player.name}: ${getString(result)}") }
    }

    private fun getDealerResult(playerResults: List<Result>): String = buildString {
        val win = playerResults.lose()
        val lose = playerResults.win()
        val push = playerResults.push()

        append("딜러:")
        if (win > 0) append(" ${win}승")
        if (lose > 0) append(" ${lose}패")
        if (push > 0) append(" ${push}패")
        appendLine()
    }
}
