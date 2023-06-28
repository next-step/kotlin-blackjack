package blackjack.domain.rule

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MoneyTest {
    @ParameterizedTest
    @CsvSource(
        "10000, 20000, 30000",
        "10000, -30000, -20000"
    )
    fun `Money 는 덧셈 연산이 가능하다`(a: Int, b: Int, result: Int) {
        val aMoney = Money(a)
        val bMoney = Money(b)

        val money = aMoney + bMoney

        money.value shouldBe result
    }
}
