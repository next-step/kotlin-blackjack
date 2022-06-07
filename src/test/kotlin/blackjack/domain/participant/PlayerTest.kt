package blackjack.domain.participant

import blackjack.domain.CLUB_KING
import blackjack.domain.DIAMOND_ACE
import blackjack.domain.HEART_TWO
import blackjack.domain.SPADE_TEN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @Test
    fun `플레이어는 카드의 점수가 21을 초과하면 BUST 된다`() {
        // given
        val player = Player("pug")

        // when
        player.addCards(DIAMOND_ACE, CLUB_KING, SPADE_TEN, HEART_TWO)

        // then
        assertThat(player.status).isEqualTo(ParticipantStatus.BUST)
    }

    @Test
    fun `플레이어는 카드의 점수가 21을 초과하지 않으면 BUST 되지 않는다`() {
        // given
        val player = Player("pug")

        // when
        player.addCards(DIAMOND_ACE, CLUB_KING, SPADE_TEN)

        // then
        assertThat(player.status).isNotEqualTo(ParticipantStatus.BUST)
    }
}
