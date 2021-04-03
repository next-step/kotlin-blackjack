package blackjack.domain.card

import blackjack.domain.CLUB_ACE
import blackjack.domain.CLUB_KING
import blackjack.domain.CLUB_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun score() {
        val cards1 = Cards(arrayListOf(CLUB_ACE, CLUB_TWO))
        assertThat(cards1.score()).isEqualTo(Score(13))

        val cards2 = Cards(arrayListOf(CLUB_ACE, CLUB_ACE))
        assertThat(cards2.score()).isEqualTo(Score(12))

        val cards3 = Cards(arrayListOf(CLUB_ACE, CLUB_KING))
        assertThat(cards3.score()).isEqualTo(Score(21))
    }
}
