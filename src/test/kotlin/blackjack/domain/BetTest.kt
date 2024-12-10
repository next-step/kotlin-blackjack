package blackjack.domain

import io.kotest.matchers.shouldBe
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

    @Test
    fun `덧셈을 한다`() {
        Bet(1L) + Bet(2L) shouldBe Bet(3L)
    }

    @Test
    fun `뺄셈을 한다`() {
        Bet(5L) - Bet(2L) shouldBe Bet(3L)
    }

    @Test
    fun `곱셈을 한다`() {
        Bet(2L) * 1.5 shouldBe Bet(3.0)
    }
}
