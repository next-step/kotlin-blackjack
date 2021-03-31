package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MoneyTest {

    @Test
    fun `곱하기 테스트`() {
        val money = Money(10000)

        assertThat(money * 2.0).isEqualTo(Money(20000))
    }
}
