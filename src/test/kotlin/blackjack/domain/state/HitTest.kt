package blackjack.domain.state

import blackjack.domain.CLUB_ACE
import blackjack.domain.CLUB_KING
import blackjack.domain.CLUB_TEN
import blackjack.domain.CLUB_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun hit() {
        val hit = Hit(CLUB_TEN, CLUB_TWO)
        val state = hit.draw(CLUB_ACE)
        assertThat(state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun bust() {
        val hit = Hit(CLUB_TEN, CLUB_TWO)
        val state = hit.draw(CLUB_KING)
        assertThat(state).isInstanceOf(Bust::class.java)
    }

    @Test
    fun hitAndBust() {
        val hit = Hit(CLUB_TEN, CLUB_TWO, CLUB_ACE)
        assertThat(hit).isInstanceOf(Hit::class.java)

        val state = hit.draw(CLUB_KING)
        assertThat(state).isInstanceOf(Bust::class.java)
    }

    @Test
    fun stay() {
        val hit = Hit(CLUB_TEN, CLUB_TWO)
        val state = hit.stay()
        assertThat(state).isInstanceOf(Stay::class.java)
    }
}
