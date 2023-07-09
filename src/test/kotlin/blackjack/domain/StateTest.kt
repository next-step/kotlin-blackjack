package blackjack.domain

import blackjack.BLACKJACK_HANDS
import blackjack.CLUBS_TWO
import blackjack.HEARTS_KING
import blackjack.SPADES_KING
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class StateTest {
    @Test
    fun `hands의 sum이 21을 초과하면 BUST다`() {
        val hands = Hands(listOf(SPADES_KING, HEARTS_KING))
        hands.hit(CLUBS_TWO)

        State.from(hands) shouldBe State.BUST
    }

    @Test
    fun `카드가 2장이고 합이 21이면 BLACKJACK이다`() {
        val hands = BLACKJACK_HANDS

        State.from(hands) shouldBe State.BLACKJACK
    }
}
