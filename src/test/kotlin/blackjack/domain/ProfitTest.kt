package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ProfitTest {
    @Test
    fun `블랙잭이면 수익은 배팅금의 1_5배이다`() {
        Profit.of(BettingAmount(15000), true).toString().toInt() shouldBe 22500
    }
}
