package blackjack.domain

import blackjack.support.Fixtures.createHand
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@Suppress("NonAsciiCharacters")
class BustedTest {
    @Test
    fun `두 장 이하인 경우 예외를 던진다`() {
        shouldThrow<IllegalArgumentException> { Busted(createHand(Rank.TWO, Rank.THREE)) }
    }

    @Test
    fun `합이 21 이하인 경우 예외를 던진다`() {
        shouldThrow<IllegalArgumentException> { Busted(createHand(Rank.FIVE, Rank.SEVEN, Rank.NINE)) }
    }

    @Test
    fun `수익은 베팅 금액을 잃는다`() {
        val state = Busted(createHand(Rank.FIVE, Rank.SEVEN, Rank.TEN))

        val profit = state.profit(Bet(1_000L))

        profit shouldBe BigDecimal(-1_000L)
    }
}
