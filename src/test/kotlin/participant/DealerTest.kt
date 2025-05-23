package participant

import CLUB_SEVEN
import Hand
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import state.Hit

class DealerTest {
    @Test
    fun `Return dealers first cards`() {
        // given
        val hand = Hand(listOf(CLUB_SEVEN, CLUB_SEVEN))
        val hit = Hit(hand)
        val dealer = Dealer(state = hit)
        val expected = listOf(CLUB_SEVEN)

        // when
        val actual = dealer.showCardFirst()

        // then
        Assertions.assertThat(actual).isEqualTo(expected)
    }
}
