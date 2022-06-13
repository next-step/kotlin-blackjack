package blackjack.view

import blackjack.domain.dto.ProfitResult
import java.text.DecimalFormat

class ProfitView(private val io: IO) {

    fun printProfitResult(profit: List<ProfitResult>) {
        io.print("## 최종 수익")
        profit.forEach {
            io.print("${it.name}: ${decimalFormat.format(it.profit)}")
        }
    }

    companion object {
        private val decimalFormat = DecimalFormat("0.#")
    }
}
