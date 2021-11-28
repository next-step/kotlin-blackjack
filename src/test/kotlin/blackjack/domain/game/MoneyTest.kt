package blackjack.domain.game

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

@Suppress("NonAsciiCharacters")
class MoneyTest {

    @Test
    fun `1000원 단위의 Money 생성`() {
        assertDoesNotThrow {
            Money(1000)
        }
    }

    @Test
    fun `돈은 음수가 될 수 없다`() {
        assertThrows<IllegalArgumentException> {
            Money(-1)
        }
    }

    @Test
    fun `돈은 1000원 단위여야 한다`() {
        assertThrows<IllegalArgumentException> {
            Money(1001)
        }
    }
}
