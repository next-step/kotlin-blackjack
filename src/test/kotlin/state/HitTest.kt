package state

import CLUB_ACE
import CLUB_KING
import CLUB_TWO
import Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class HitTest {
    @Test
    fun `Return Bust when the sum is over than 21`() {
        // given
        val hand = Hand(listOf(CLUB_KING, CLUB_KING))
        val hit = Hit(hand)

        // when
        val actual = hit.drawCards(listOf(CLUB_KING))

        // then
        assertThat(actual).isInstanceOf(Bust::class.java)
    }

    @Test
    fun `Return Hit when the sum is less than 21`() {
        // given
        val hand = Hand(listOf(CLUB_TWO, CLUB_TWO))
        val hit = Hit(hand)

        // when
        val actual = hit.drawCards(listOf(CLUB_TWO))

        // then
        assertThat(actual).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `Return Hit when the sum is  21`() {
        // given
        val hand = Hand(listOf(CLUB_KING, CLUB_KING))
        val hit = Hit(hand)

        // when
        val actual = hit.drawCards(listOf(CLUB_ACE))

        // then
        assertThat(actual).isInstanceOf(Hit::class.java)
    }
}
