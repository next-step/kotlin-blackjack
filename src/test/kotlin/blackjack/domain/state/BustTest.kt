package blackjack.domain.state

import blackjack.domain.CLUBS_ACE
import blackjack.domain.CLUBS_KING
import blackjack.domain.CLUBS_TEN
import blackjack.domain.CLUBS_TWO
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BustTest {
    @Test
    fun constructor() {
        assertThrows<IllegalArgumentException> {
            Bust(Hands())
        }
        assertThrows<IllegalArgumentException> {
            Bust(Hands(CLUBS_ACE))
        }
    }

    @Test
    fun draw() {
        val actual = Bust(Hands(CLUBS_TWO, CLUBS_TEN, CLUBS_KING))
        shouldThrow<IllegalArgumentException> { actual.draw(CLUBS_KING) }
    }
}
