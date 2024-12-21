package blackjack.domain

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.math.BigDecimal

@Suppress("NonAsciiCharacters")
class BetTest {
    @Test
    fun `베팅 금액은 0보다 커야한다`() {
        assertThrows<IllegalArgumentException> { Bet(BigDecimal.ZERO) }
        assertThrows<IllegalArgumentException> { Bet(BigDecimal(-1)) }
    }
}
