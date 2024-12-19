package blackjack.domain

import blackjack.support.Fixtures.createHand
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

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
}
