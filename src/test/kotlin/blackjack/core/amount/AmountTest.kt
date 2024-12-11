package blackjack.core.amount

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class AmountTest {

    @Test
    fun `생성자 테스트`() {
        shouldThrow<IllegalArgumentException> { Amount(-1) }
    }

    @Test
    fun `곱연산자 테스트`() {
        val amount = Amount(10) * 2.0f
        amount.amount shouldBe 20
    }
}