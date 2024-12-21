package blackjack.domain

import blackjack.support.Fixtures.createHand
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.math.BigDecimal

@Suppress("NonAsciiCharacters")
class BlackjackTest {
    @Test
    fun `카드의 숫자가 둘이 아닌 경우 예외를 던진다`() {
        assertSoftly {
            shouldThrow<IllegalArgumentException> { Blackjack(createHand(Rank.TWO)) }
            shouldThrow<IllegalArgumentException> { Blackjack(createHand(Rank.TWO, Rank.THREE, Rank.FOUR)) }
        }
    }

    @Test
    fun `합이 21이 아닌 경우 예외를 던진다`() {
        shouldThrow<IllegalArgumentException> { Blackjack(createHand(Rank.KING, Rank.TEN)) }
    }

    @Test
    fun `수익은 베팅 금액의 1_5배이다`() {
        val state = Blackjack(createHand(Rank.ACE, Rank.KING))

        val profit = state.profit(Bet(1_000L))

        profit shouldBe BigDecimal(1_500L)
    }
}
