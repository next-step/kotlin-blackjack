package blackjack.model.profit

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProfitsTest {

    @Test
    fun `딜러 수익`() {
        // given
        val profits = Profits(
            listOf(
                Profit("mark", 1000),
                Profit("dean", -1000),
                Profit("harry", 2000)
            )
        )

        // when
        val actual = profits.dealerProfit().profit

        // then
        assertThat(actual).isEqualTo(-2000)
    }
}
