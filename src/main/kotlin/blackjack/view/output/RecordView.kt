package blackjack.view.output

import blackjack.view.dto.RecordDto

object RecordView {
    fun print(dto: RecordDto) {
        val winCount = dto.records.count { !it.value }
        val loseCount = dto.records.count { it.value }
        println("## 최종 승패")
        println("${dto.dealerName}: ${winCount}승 ${loseCount}패")
        dto.records.forEach { (name, isWin) -> println("$name: ${if (isWin) "승" else "패"}") }
    }
}
