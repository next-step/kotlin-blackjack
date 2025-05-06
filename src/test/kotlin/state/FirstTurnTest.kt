package state

import CLUB_ACE
import CLUB_KING
import Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class FirstTurnTest {
    @Test
    fun `When draw two cards with 21 sum then return Blackjack`() {
        // given
        val hand = Hand(emptyList())
        val firstTurn = FirstTurn(hand)

        // when
        val actual = firstTurn.drawCards(listOf(CLUB_KING, CLUB_ACE))

        // then
        assertThat(actual).isInstanceOf(Blackjack::class.java)
    }

    @Test
    fun `When draw two cards less than 21 sum then return Hit`() {
        // given
        val hand = Hand(emptyList())
        val firstTurn = FirstTurn(hand)

        // when
        val actual = firstTurn.drawCards(listOf(CLUB_KING, CLUB_KING))

        // then
        assertThat(actual).isInstanceOf(Hit::class.java)
    }
}
