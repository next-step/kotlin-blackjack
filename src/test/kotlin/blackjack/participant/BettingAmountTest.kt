package blackjack.participant

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BettingAmountTest {

    @Test
    fun `베팅 금액은 금액을 가진다`() {
        val amount = BettingAmount(1000)
        amount.amount shouldBe 1000
    }
}
