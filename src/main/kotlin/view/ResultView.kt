package view

import domain.Match
import domain.Player

object ResultView {

    fun displayPlayersScore(players: List<Player>) {
        println()
        players.forEach {
            print(it.name + " : " + InputView.getCardString(it.cards))
            println(" - 결과 : ${it.getSumOfCards()}")
        }
    }

    fun displayWinOrLose(players: List<Player>) {
        println()
        println("## 최종 승패")
        players.forEach {
            println(getMatch(it))
        }
    }

    fun displayRevenue(revenue: Map<String, Int>) {
        println()
        println("## 최종 수익")

        revenue.forEach {
            println(REVENUE_FORMAT.format(it.key, it.value))
        }
    }

    private fun getMatch(player: Player): String {
        return when (player.match) {
            Match.WIN -> "${player.name}: 승"
            Match.LOSE -> "${player.name}: 패"
        }
    }

    const val REVENUE_FORMAT = "%s: %d"
}
