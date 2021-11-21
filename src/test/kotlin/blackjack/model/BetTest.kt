package blackjack.model

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BetTest {

    @Test
    fun `배팅 금액은 0보다 작을 수 없다`() {
        assertThrows<RuntimeException> { Bet(-1) }
    }
}
