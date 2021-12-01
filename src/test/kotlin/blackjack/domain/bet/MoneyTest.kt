package blackjack.domain.bet

import blackjack.error.InvalidMoneyRangeException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("돈(Money)")
internal class MoneyTest {

    @ParameterizedTest(name = "입력 값 : {0}")
    @ValueSource(ints = [0, 1, 10, 100, Integer.MAX_VALUE])
    fun `돈은 0 이상의 정수로 이루어줘야 한다`(amount: Int) {
        val money = Money(amount)

        assertAll(
            { assertThat(money).isNotNull },
            { assertThat(money).isExactlyInstanceOf(Money::class.java) }
        )
    }

    @ParameterizedTest(name = "입력 값 : {0}")
    @ValueSource(ints = [-1, -10, -100, -1000, Integer.MIN_VALUE])
    fun `돈은 0 미만의 정수로 이루어지면 안 된다`(amount: Int) {
        val exception = assertThrows<InvalidMoneyRangeException> { Money(amount) }

        assertThat(exception.message).isEqualTo("'%s'는 돈의 유효한 범위가 아닙니다".format(amount))
    }

    @ParameterizedTest(name = "입력 값 : {0}")
    @CsvSource(value = ["1:1:2", "1000:1000:2000", "10000:15000:25000"], delimiter = ':')
    fun `돈은 다른 돈의 액수를 더할 수 있다`(firstAmount: Int, secondAmount: Int, expected: Int) {
        val firstBetAmount = Money(firstAmount)
        val secondBetAmount = Money(secondAmount)

        val betAmount = firstBetAmount + secondBetAmount
        assertThat(betAmount.money).isEqualTo(expected)
    }
}
