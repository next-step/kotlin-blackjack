package blackjack.business.util

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MoneyTest {
    @Test
    fun `금액은 0 이상이여야 한다`() {
        // given
        val value = -1

        // when,then
        shouldThrow<IllegalArgumentException> { Money(value) }.message shouldBe "금액은 0 이상이여야 합니다."
    }
}
