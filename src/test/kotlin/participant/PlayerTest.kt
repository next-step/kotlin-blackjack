package participant

import CLUB_SEVEN
import CLUB_TWO
import Hand
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import state.Hit

class PlayerTest {
    @Test
    fun `Add card to hand`() {
        // given
        val hand = Hand(listOf(CLUB_SEVEN, CLUB_SEVEN))
        val hit = Hit(hand)
        val player = Player("krrong", hit)
        val expected = 3

        // when
        player.drawCards(listOf(CLUB_TWO))

        // then
        assertThat(player.state.hand.size).isEqualTo(expected)
    }

    @Test
    fun `Return players cards`() {
        // given
        val hand = Hand(listOf(CLUB_SEVEN, CLUB_SEVEN))
        val hit = Hit(hand)
        val player = Player("krrong", hit)
        val expected = listOf(CLUB_SEVEN, CLUB_SEVEN)

        // when
        val actual = player.showCardFirst()

        // then
        assertThat(actual).isEqualTo(expected)
    }
}
