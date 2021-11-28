package blackjack.domain.player

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@DisplayName("돈(Money)")
internal class MoneyTest {

    @ParameterizedTest(name = "입력 값 : {0}")
    @ValueSource(ints = [0, 1, 10, 100, Integer.MAX_VALUE])
    fun `0 이상의 정수로 돈을 표현할 수 있다`(moneyInt: Int) {
        val money = Money(moneyInt)

        assertAll(
            { assertThat(money).isNotNull },
            { assertThat(money).isExactlyInstanceOf(Money::class.java) }
        )
    }
}
