package blackjack.view

import blackjack.domain.Dealer
import blackjack.domain.GameStatus
import blackjack.domain.Player

class WinnerView(private val io: IO) {

    fun printWinnerResult(status: GameStatus) {
        io.print("## 최종 승패")
        printDealerResult(status)
        printPlayerResult(status)
    }

    private fun printDealerResult(status: GameStatus) {
        val dealer = status.dealer
        val players = status.players
        val win = players.count { dealer > it }
        val lose = players.count { dealer < it }
        val draw = players.size - win - lose
        val result = listOf(win.addSuffix("승"), draw.addSuffix("무"), lose.addSuffix("패"))
            .filter { it.isNotBlank() }
            .joinToString(" ")

        io.print("딜러: $result")
    }

    private fun Int.addSuffix(suffix: String): String = if (this > 0) "$this$suffix" else ""

    private fun printPlayerResult(status: GameStatus) {
        status.players.forEach { io.print("${it.name}: ${it.result(status.dealer)}") }
    }

    private fun Player.result(dealer: Dealer): String {
        if (dealer > this) return "패"
        if (dealer < this) return "승"
        return "무"
    }
}
