package blackjack.view

import blackjack.model.Dealer
import blackjack.model.Gamer
import blackjack.model.Gamers
import blackjack.model.Player
import blackjack.view.res.Resource
import blackjack.view.res.getString

class OutputView {

    fun printReady(gamers: Gamers) {
        val names = gamers.players().map { it.name }.toList()
        println(Resource.STR_FIRST_DRAW_CARD.format(names.joinToString(), 2))
        gamers.toList().forEach { player -> printCards(player, true) }
        println()
    }

    fun printRunning(gamer: Gamer) = when (gamer) {
        is Dealer -> println(Resource.STR_RUNNING_DEALER_MORE_CARD)
        is Player -> printCards(gamer, true)
    }

    private fun printCards(gamer: Gamer, newline: Boolean) {
        val cardsDisplay = gamer.cards.toList().joinToString {
            "${it.denomination.symbol}${getString(it.suit)}"
        }
        print(Resource.STR_CARDS.format(gamer.name, cardsDisplay))
        if (newline) println()
    }

    fun printResult(gamers: Gamers) {
        val dealer = gamers.dealer() ?: return
        val players = gamers.players()

        // 딜러 카드
        println()
        printCards(dealer, false)
        println(Resource.STR_RESULT_SCORE.format(dealer.score))

        // 플레이어 카드
        players.forEach { player ->
            printCards(player, false)
            println(Resource.STR_RESULT_SCORE.format(player.score))
        }

        // 최종 수익
        println()
        println(Resource.STR_FINAL_PROFIT)
        players
            .map { it.profit(dealer.state) }
            .sumOf { -it.amount.roundToInt() }
            .let { profit -> println(Resource.STR_DEALER_PROFIT.format(profit)) }
        players.forEach { player -> println("${player.name}: ${player.profit(dealer.state)}") }
    }
}
