package blackjack.core.amount

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class ProfitAmountTest {

    @Suppress("NonAsciiCharacters")
    @Test
    fun `연산자를 테스트 한다`() {
        var profitAmount = ProfitAmount()
        profitAmount -= Amount(1000)
        profitAmount.amount shouldBe -1000

        profitAmount += Amount(2000)
        profitAmount.amount shouldBe 1000
    }
}