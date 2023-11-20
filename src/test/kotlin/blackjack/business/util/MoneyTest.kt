package blackjack.business.util

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `금액을 잃는 금액으로 변경`() {
        // given
        val money = Money(1000)

        // when
        val actual = money.lose()

        // then
        actual shouldBe Money(-1000)
    }
}
