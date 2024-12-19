package blackjack.domain

import blackjack.support.Fixtures.createHand
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@Suppress("NonAsciiCharacters")
class StandTest {
    @Test
    fun `두 장 미만일 경우 예외를 던진다`() {
        shouldThrow<IllegalArgumentException> { Stand(createHand(Rank.TWO)) }
    }

    @Test
    fun `21점을 초과하면 예외를 던진다`() {
        shouldThrow<IllegalArgumentException> { Stand(createHand(Rank.FIVE, Rank.SEVEN, Rank.TEN)) }
    }

    @Test
    fun `수익은 1배 이다`() {
        val state = Stand(createHand(Rank.NINE, Rank.TEN))

        val profit = state.profit(Bet(1_000L))

        profit shouldBe BigDecimal(1_000L)
    }
}
