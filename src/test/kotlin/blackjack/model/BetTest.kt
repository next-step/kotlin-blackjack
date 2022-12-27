package blackjack.model

import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

internal class BetTest {

    @Test
    fun `베팅 금액은 0보다 커야 한다`() {
        assertThrows(IllegalArgumentException::class.java) {
            Bet(0)
        }
    }
}
