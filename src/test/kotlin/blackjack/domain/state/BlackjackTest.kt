package blackjack.domain.state

import blackjack.domain.CLUBS_ACE
import blackjack.domain.CLUBS_TEN
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BlackjackTest {
    @Test
    fun `생성자 테스트`() {
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
        val actual = state.profit(1_000)
        actual shouldBe 1_500
    }
}
