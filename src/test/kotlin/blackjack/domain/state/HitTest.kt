package blackjack.domain.state

import blackjack.domain.*
import io.kotest.matchers.collections.shouldContainAnyOf
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class HitTest {

    @Test
    fun `생성자 테스트`() {

        assertThrows<IllegalArgumentException> {
            Hit(Hands())
        }
        assertThrows<IllegalArgumentException> {
            Hit(Hands(CLUBS_ACE))
        }
    }

    @Test
    fun hit(){
        val state = Hit(Hands(CLUBS_ACE, CLUBS_THREE))
        val acutal = state.draw(CLUBS_TEN)
        acutal.shouldBeInstanceOf<Hit>()
        acutal.cards.shouldContainAnyOf(CLUBS_TWO, CLUBS_KING, CLUBS_TEN)

    }

    @Test
    fun bust(){
        val state = Hit(Hands(CLUBS_ACE, CLUBS_KING))
        val acutal = state.draw(CLUBS_TEN)
        acutal.shouldBeInstanceOf<Bust>()
    }

    @Test
    fun stay(){
        val state = Hit(Hands(CLUBS_ACE, CLUBS_KING))
        val acutal = state.stay(CLUBS_TWO)
        acutal.shouldBeInstanceOf<Stay>()
    }
}
