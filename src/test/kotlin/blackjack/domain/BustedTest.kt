package blackjack.domain

import blackjack.support.Fixtures.createHand
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

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
}
