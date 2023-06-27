package blackjack.view

import blackjack.domain.player.Participants
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerImpl
import blackjack.domain.rule.Score

object OutputView {
    fun printGameStart(names: List<String>, initialDraw: Int) {
        println("\n딜러와 ${names.joinToString(", ")}에게 ${initialDraw}장을 나누었습니다.")
    }

    fun printPlayerCard(players: Participants) {
        players.players.forEach {
            printPlayerCard(it)
        }
        println()
    }

    fun printCard(players: Participants) {
        printDealerCard(players)
        printPlayerCard(players)
    }

    private fun printDealerCard(players: Participants) {
        println(players.dealer.showOnlyOneCard())
    }

    fun printDealerGetAdditionalCard() {
        println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n")
    }

    fun printPlayerCard(player: PlayerImpl) {
        println("${player.name}카드: ${player.cardHold.getAllCards().joinToString(", ") { it.rank.mark + it.shape.mark } }")
    }

    fun printPlayerResult(players: Participants) {
        println()
        players.allPlayers.forEach {
            printPlayerResult(it)
        }
    }

    private fun printPlayerResult(player: Player) {
        println("${player.name}카드: ${player.cardHold.getAllCards().joinToString(", ") { it.rank.mark + it.shape.mark } } - 결과: ${player.getPoints()}")
    }

    fun showWinner(results: Map<String, Score>) {
        println("\n## 최종 승패")
        results.forEach { (name, score) ->
            println("$name:  ${showWin(score)}${showDraw(score)}${showLose(score)}")
        }
    }

    private fun showWin(score: Score): String {
        if (score.getTotalGame() <= 1) return showSimpleWin(score)
        return if (score.winNum >= 1) score.winNum.toString() + "승 " else ""
    }

    private fun showDraw(score: Score): String {
        if (score.getTotalGame() <= 1) return showSimpleDraw(score)
        return if (score.drawNum >= 1) score.drawNum.toString() + "무 " else ""
    }

    private fun showLose(score: Score): String {
        if (score.getTotalGame() <= 1) return showSimpleLose(score)
        return if (score.loseNum >= 1) score.loseNum.toString() + "패 " else ""
    }

    private fun showSimpleWin(score: Score): String {
        return if (score.winNum >= 1) "승 " else ""
    }

    private fun showSimpleDraw(score: Score): String {
        return if (score.drawNum >= 1) "무 " else ""
    }

    private fun showSimpleLose(score: Score): String {
        return if (score.loseNum >= 1) "패 " else ""
    }
}
