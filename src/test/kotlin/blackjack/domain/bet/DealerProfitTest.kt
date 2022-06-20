package blackjack.domain.bet

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class DealerProfitTest {
    @ParameterizedTest(name = "플레이어의 총 수입이 {0}, {1}, {2}일 경우 딜러의 수입은 {3}이다")
    @CsvSource(
        "100.0, -200.0, 0.0, 100.0",
        "100.0, -200.0, 100.0, 0.0",
        "100.0, -200.0, 200.0, -100.0"
    )
    fun `딜러의 수익은 플레이어의 총 수입의 합에 -1을 곱한 것과 같다`(
        playerProfit1: Double,
        playerProfit2: Double,
        playerProfit3: Double,
        dealerProfit: Double
    ) {
        assertThat(
            DealerProfit(listOf(PlayerProfit(playerProfit1), PlayerProfit(playerProfit2), PlayerProfit(playerProfit3))).profit.value
        ).isEqualTo(dealerProfit)
    }
}

private fun PlayerProfit(profit: Double) = PlayerProfit("vivian", Profit(profit))
