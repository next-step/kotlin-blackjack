package blackjack.domain.participant

import blackjack.CardFixtures.CLUB_KING
import blackjack.CardFixtures.DIAMOND_ACE
import blackjack.CardFixtures.SPADE_TEN
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class HandTest {

    @Test
    fun `플레이어의 핸드에 카드를 추가할 수 있다`() {
        // given
        val hand = Hand()

        // when
        hand.add(DIAMOND_ACE)
            .add(CLUB_KING)
            .add(SPADE_TEN)

        // then
        assertThat(hand.cards).hasSize(3)
        assertThat(hand.cards).containsExactly(DIAMOND_ACE, CLUB_KING, SPADE_TEN)
    }

    @Test
    fun `플레이어의 핸드에 있는 카드의 총 점수를 계산할 수 있다`() {
        // given
        val hand = Hand()

        // when
        hand.add(DIAMOND_ACE)
            .add(CLUB_KING)
            .add(SPADE_TEN)
        val score = hand.score()

        // then
        assertThat(score).isEqualTo(21)
    }
}
