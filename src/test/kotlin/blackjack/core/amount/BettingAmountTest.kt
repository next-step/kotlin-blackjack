package blackjack.core.amount

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class BettingAmountTest {

    @Test
    fun `생성자 테스트`() {
        shouldThrow<IllegalArgumentException> { BettingAmount(-1) }
    }
}