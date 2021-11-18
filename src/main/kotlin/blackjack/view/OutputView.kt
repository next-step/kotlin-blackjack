package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Gamer
import blackjack.model.Gamers
import blackjack.view.res.getString

class OutputView {

    fun printFirstDraw(players: Gamers, drawCount: Int) {
        val names = players.filterPlayers().toNames().toList()
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

    fun printResult(gamers: Gamers) {
        println()
        gamers.toList().forEach { gamer ->
            printCards(gamer, false)
            println(" - 결과: ${gamer.cards.sum()}")
        }
    }
}
