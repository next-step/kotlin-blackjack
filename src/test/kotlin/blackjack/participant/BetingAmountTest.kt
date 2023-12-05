package blackjack.participant

import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class BetingAmountTest {

    @Test
    fun `베팅 금액은 0이하일 수 없다`() {
        shouldThrow<IllegalArgumentException> { BetingAmount(-1) }
    }
}
