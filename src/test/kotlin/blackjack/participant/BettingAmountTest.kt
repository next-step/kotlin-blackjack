package blackjack.participant

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BettingAmountTest {

    @Test
    fun `베팅 금액은 금액을 가진다`() {
        val amount = BettingAmount(1000)
        amount.amount shouldBe 1000
    }

    @Test
    fun `베팅 금액을 더한다`() {
        val amount1 = BettingAmount(1000)
        val amount2 = BettingAmount(2000)
        val actual = amount1 + amount2

        actual.amount shouldBe 3000
    }

    @Test
    fun `베팅 금액을 뺀다`() {
        val amount1 = BettingAmount(1000)
        val amount2 = BettingAmount(2000)
        val actual = amount1 - amount2

        actual.amount shouldBe -1000
    }

    @Test
    fun `베팅 금액을 음수로 만든다`() {
        val amount = BettingAmount(1000)
        amount.unaryMinus().amount shouldBe -1000
    }
}
