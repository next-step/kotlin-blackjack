package blackjack.domain.state

import blackjack.domain.CLUB_ACE
import blackjack.domain.CLUB_KING
import blackjack.domain.CLUB_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FirstTurnTest {
    @Test
    fun blackjack() {
        val firstTurn = FirstTurn()
        val state = firstTurn.draw(CLUB_ACE, CLUB_KING)
        assertThat(state).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun hit() {
        val firstTurn = FirstTurn()
        val state = firstTurn.draw(CLUB_ACE, CLUB_TWO)
        assertThat(state).isInstanceOf(Hit::class.java)
    }
}