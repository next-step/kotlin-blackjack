package blackjack.domain.player

import blackjack.fixture.CardFixture
import blackjack.fixture.PlayerFixture
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    @Test
    fun `카드의 총 합이 21이하이면 카드를 더 뽑을 수 있는 상태라고 판단한다`() {
        // given
        val cards = CardFixture.CARDS_WITH_SUM_18
        val player = PlayerFixture.플레이어_생성(cards)

        // when
        val isAbleToDraw = player.isAbleToDraw()

        // then
        assertThat(isAbleToDraw).isTrue
    }

    @Test
    fun `카드의 총 합이 21초과이면 카드를 더 뽑을 수 없는 상태라고 판단한다`() {
        // given
        val cards = CardFixture.CARDS_WITH_SUM_30
        val player = PlayerFixture.플레이어_생성(cards)

        // when
        val isAbleToDraw = player.isAbleToDraw()

        // then
        assertThat(isAbleToDraw).isFalse
    }

    @Test
    fun `사용자는 게임을 그만둘 수 있다`() {
        // given
        val cards = CardFixture.CARDS_WITH_SUM_18
        val player = PlayerFixture.플레이어_생성(cards)

        // when
        val stoppedPlayer = player.death()

        // then
        assertAll(
            { assertThat(stoppedPlayer.finished).isTrue },
            { assertThat(stoppedPlayer.isAbleToDraw()).isFalse }
        )
    }

    @Test
    fun `사용자는 점수를 계산할 수 있다`() {
        // given
        val cards = CardFixture.CARDS_WITH_SUM_18
        val player = PlayerFixture.플레이어_생성(cards)
        val expected = 18

        // when
        val result = player.score()

        // then
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `사용자는 현재 카드를 갱신할 수 있다`() {
        // given
        val cards = CardFixture.CARDS_WITH_SUM_18
        val player = PlayerFixture.플레이어_생성(cards)

        // when
        val afterRenewal = player.renewal(CardFixture.DIAMOND_KING)

        // then
        assertAll(
            { assertThat(player.score()).isLessThan(afterRenewal.score()) },
            { assertThat(afterRenewal.cards.values).containsAll(cards.values + CardFixture.DIAMOND_KING) },
        )
    }
}
