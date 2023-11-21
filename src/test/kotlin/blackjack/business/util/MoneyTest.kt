package blackjack.business.util

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `잃는 금액을 돌려준다`() {
        // given
        val money = Money(1000)

        // when
        val actual = money.lose()

        // then
        actual shouldBe -1000
    }

    @Test
    fun `돈은 최소값보다 작을 수 없다`() {
        shouldThrow<IllegalArgumentException> { Money(-1) }.message shouldBe "돈은 최소값보다 작을 수 없습니다."
    }
}
