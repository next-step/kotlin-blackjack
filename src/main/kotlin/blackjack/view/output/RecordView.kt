package blackjack.view.output

import blackjack.view.dto.RecordDto

object RecordView {
    fun print(dto: List<RecordDto>) {
        println("## 최종 수익")
        dto.forEach { (name, profit) -> println("$name: $profit") }
    }
}
