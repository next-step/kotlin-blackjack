package blackjack.domain

import blackjack.support.Fixtures.createHand
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

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
}
