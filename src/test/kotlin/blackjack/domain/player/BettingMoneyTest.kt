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
internal class BettingMoneyTest {

    @ParameterizedTest(name = "입력 값 : {0}")
    @ValueSource(ints = [0, 1, 10, 100, Integer.MAX_VALUE])
    fun `0 이상의 정수로 배팅 금액을 표현할 수 있다`(moneyInt: Int) {
        val bettingMoney = BettingMoney(moneyInt)

        assertAll(
            { assertThat(bettingMoney).isNotNull },
            { assertThat(bettingMoney).isExactlyInstanceOf(BettingMoney::class.java) }
        )
    }

    @ParameterizedTest(name = "입력 값 : {0}")
    @ValueSource(ints = [-1, -10, -100, -1000, Integer.MIN_VALUE])
    fun `0 미만의 정수로 배팅 금액을 표현할 수 없다`(moneyInt: Int) {
        val exception = assertThrows<InvalidMoneyRangeException> { BettingMoney(moneyInt) }

        assertThat(exception.message).isEqualTo("'%s'는 돈의 유효한 범위가 아닙니다".format(moneyInt))
    }

    @ParameterizedTest(name = "입력 값 : {0}")
    @CsvSource(value = ["0:1.5:0", "1:1.5:1", "10:1.5:15", "100:1.5:150"], delimiter = ':')
    fun `비율이 들어오면, 계산 후 배팅 금액(정수)을 반환한다`(moneyInt: Int, betRate: Int, expected: Int) {
        val bettingMoney = BettingMoney()

        val earningMoney = bettingMoney.winBet(betRate)
        asertThat(earningMoney.money).isEqualTo(expected)
    }
}
