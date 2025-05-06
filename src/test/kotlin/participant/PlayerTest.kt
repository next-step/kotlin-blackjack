package participant

import CLUB_SEVEN
import CLUB_TWO
import Hand
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `Add card to hand`() {
        // given
        val hand = Hand(listOf(CLUB_SEVEN, CLUB_SEVEN))
        val player = Player("krrong", hand)
        val expected = 3

        // when
        player.drawCards(listOf(CLUB_TWO))

        // then
        Assertions.assertThat(player.hand.size).isEqualTo(expected)
    }

    @Test
    fun `Return players cards`() {
        // given
        val hand = Hand(listOf(CLUB_SEVEN, CLUB_SEVEN))
        val player = Player("krrong", hand)
        val expected = listOf(CLUB_SEVEN, CLUB_SEVEN)

        // when
        val actual = player.showCardFirst()

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
