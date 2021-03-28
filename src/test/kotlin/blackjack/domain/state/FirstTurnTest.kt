package blackjack.domain.state

import blackjack.domain.CLUB_ACE
import blackjack.domain.CLUB_KING
import blackjack.domain.CLUB_TWO
import blackjack.domain.card.Cards
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class FirstTurnTest {

    @Test
    fun blackjack() {
        val firstTurn = FirstTurn()
        val cards = Cards(listOf(CLUB_ACE, CLUB_KING))
        val state = firstTurn.draw(cards)
        assertThat(state).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun hit() {
        val firstTurn = FirstTurn()
        val cards = Cards(listOf(CLUB_ACE, CLUB_TWO))
        val state = firstTurn.draw(cards)
        assertThat(state).isInstanceOf(Hit::class.java)
    }
}