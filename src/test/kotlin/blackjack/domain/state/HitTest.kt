package blackjack.domain.state

import blackjack.domain.CLUBS_ACE
import blackjack.domain.CLUBS_KING
import blackjack.domain.CLUBS_TEN
import blackjack.domain.CLUBS_THREE
import blackjack.domain.CLUBS_TWO
import io.kotest.matchers.collections.shouldContainAnyOf
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HitTest {
    @Test
    fun constructor() {
        assertThrows<IllegalArgumentException> {
            Hit(Hands())
        }
        assertThrows<IllegalArgumentException> {
            Hit(Hands(CLUBS_ACE))
        }
    }

    @Test
    fun hit() {
        val state = Hit(Hands(CLUBS_TWO, CLUBS_THREE))
        val acutal = state.draw(CLUBS_TEN)
        acutal.shouldBeInstanceOf<Hit>()
        acutal.hands.shouldContainAnyOf(CLUBS_TWO, CLUBS_THREE, CLUBS_TEN)
    }

    @Test
    fun bust() {
        val state = Hit(Hands(CLUBS_TEN, CLUBS_KING))
        val acutal = state.draw(CLUBS_TEN)
        acutal.shouldBeInstanceOf<Bust>()
    }

    @Test
    fun stay() {
        val state = Hit(Hands(CLUBS_ACE, CLUBS_KING))
        val acutal = state.stay()
        acutal.shouldBeInstanceOf<Stay>()
    }
}
