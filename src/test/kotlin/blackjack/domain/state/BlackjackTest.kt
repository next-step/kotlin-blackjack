package blackjack.domain.state

import blackjack.domain.CLUBS_ACE
import blackjack.domain.CLUBS_TEN
import blackjack.domain.player.DealerState
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackjackTest {
    @Test
    fun constructor() {
        assertThrows<IllegalArgumentException> {
            Blackjack(Hands())
        }
        assertThrows<IllegalArgumentException> {
            Blackjack(Hands(CLUBS_ACE))
        }
    }

    @Test
    fun profit() {
        val state = Blackjack(Hands(CLUBS_ACE, CLUBS_TEN))
        val actual = state.profit(1000, DealerState(isBust = false, isBlackjack = false))
        actual shouldBe 1_500
    }
}
