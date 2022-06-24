package view

import domain.Match
import domain.Player

object ResultView {

    fun displayPlayersScore(players: List<Player>) {
        println()
        players.forEach {
            print(it.name + " : " + InputView.getCardString(it.cards))
            println("- 결과 : ${it.getSumOfCards()}")
        }
    }

    fun displayWinOrLose(players: List<Player>) {
        println()
        println("## 최종 승패")
        players.forEach {
            println(getMatch(it))
        }
    }

    private fun getMatch(player: Player): String {
        return when (player.match) {
            Match.WIN -> "${player.name}: 승"
            Match.LOSE -> "${player.name}: 패"
        }
    }
}
