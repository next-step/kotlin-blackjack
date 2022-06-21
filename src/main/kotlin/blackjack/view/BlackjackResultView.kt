package blackjack.view

import blackjack.domain.Stat
import blackjack.dto.BlackjackDto

object BlackjackResultView {

    private const val RESULT_MESSAGE = "\n## 최종 승패"

    fun printResult(blackjackDto: BlackjackDto) {
        println(RESULT_MESSAGE)
        blackjackDto.apply {
            println("${dealer.name}: ${getDealerStat(dealer.statMap)}")
            players.forEach { println("${it.name}: ${it.stat.value}") }
        }
    }

    private fun getDealerStat(stat: Map<Stat, Int>): String {
        return stat.keys.joinToString(" ") { "${stat[it]}${it.value}" }
    }
}
