package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class BettingAmountTest {
    @Test
    fun `배팅 금액이 마이너스면 예외 발생`() {
        shouldThrow<IllegalArgumentException> {
            BettingAmount(-1000)
        }
    }
}
