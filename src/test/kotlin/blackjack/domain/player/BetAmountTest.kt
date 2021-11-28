package blackjack.domain.player

import blackjack.error.InvalidMoneyRangeException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("배팅 금액(BettingMoney)")
internal class BetAmountTest {

    @ParameterizedTest(name = "입력 값 : {0}")
    @ValueSource(ints = [0, 1, 10, 100, Integer.MAX_VALUE])
    fun `0 이상의 정수로 배팅 금액을 표현할 수 있다`(amountInt: Int) {
        val betAmount = BetAmount(amountInt)

        assertAll(
            { assertThat(betAmount).isNotNull },
            { assertThat(betAmount).isExactlyInstanceOf(BetAmount::class.java) }
        )
    }

    @ParameterizedTest(name = "입력 값 : {0}")
    @ValueSource(ints = [-1, -10, -100, -1000, Integer.MIN_VALUE])
    fun `0 미만의 정수로 배팅 금액을 표현할 수 없다`(amountInt: Int) {
        val exception = assertThrows<InvalidMoneyRangeException> { BetAmount(amountInt) }

        assertThat(exception.message).isEqualTo("'%s'는 돈의 유효한 범위가 아닙니다".format(amountInt))
    }

    @ParameterizedTest(name = "입력 값 : {0}")
    @CsvSource(value = ["0:1.5:0", "1:1.5:1", "10:1.5:15", "100:1.5:150"], delimiter = ':')
    fun `비율이 들어오면, 계산 후 배팅 금액(정수)을 반환한다`(amountInt: Int, resultRate: Double, expected: Int) {
        val betAmount = BetAmount(amountInt)

        val earningMoney = betAmount.winBet(resultRate)
        assertThat(earningMoney.amount).isEqualTo(expected)
    }

    @ParameterizedTest(name = "입력 값 : {0}")
    @CsvSource(value = ["1:1:2", "1000:1000:2000", "10000:15000:25000"], delimiter = ':')
    fun `배팅 금액을 더할 수 있다`(firstAmount: Int, secondAmount: Int, expected: Int) {
        val firstBetAmount = BetAmount(firstAmount)
        val secondBetAmount = BetAmount(secondAmount)

        val betAmount = firstBetAmount + secondBetAmount
        assertThat(betAmount.amount).isEqualTo(expected)
    }
}
