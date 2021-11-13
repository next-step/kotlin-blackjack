package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    @Test
    fun `플레이어는 이름이 지정되어 있고, 카드를 가지고 있다`() {
        // given
        val player = Player.of("김형준")
            .receiveCard(Card(Suit.HEARTS, Denomination.ACE))
            .receiveCard(Card(Suit.SPADES, Denomination.KING))

        // then
        assertAll({
            assertThat(player.playerName).isEqualTo("김형준")
            assertThat(player.status.inquireCards().getSize()).isEqualTo(2)
        })
    }

    @Test
    fun `플레이어가 중복된 카드를 받는다면 에러`() {
        // given
        val player = Player.of("김형준")

        // when
        val actual = runCatching {
            player.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
                .receiveCard(Card(Suit.SPADES, Denomination.KING))
                .receiveCard(Card(Suit.SPADES, Denomination.KING))
        }.exceptionOrNull()

        // then
        assertThat(actual).hasMessageContaining("중복 된 카드가 있습니다.")
    }

    @Test
    fun `플레이어의 현재 점수는 11점이다`() {
        // given
        val player = Player.of("김형준")
            .receiveCard(Card(Suit.HEARTS, Denomination.ACE))
            .receiveCard(Card(Suit.SPADES, Denomination.KING))

        // when
        val currentScore = player.status.getCurrentScore()

        // then
        assertThat(currentScore).isEqualTo(11)
    }

    @Test
    fun `플레이어의 현재 점수는 11점이면 21이하이기 때문에 카드를 더 받을 수 있다`() {
        // given
        val player = Player.of("김형준")
            .receiveCard(Card(Suit.HEARTS, Denomination.ACE))
            .receiveCard(Card(Suit.SPADES, Denomination.KING))

        // when
        val decisionStatus = player.status.ableGetACard()

        // then
        assertThat(decisionStatus).isEqualTo(true)
    }

    @Test
    fun `플레이어의 현재 점수는 22점이면 21이상이기 때문에 카드를 더 받을 수 있다`() {
        // given
        val player = Player.of("김형준")
            .receiveCard(Card(Suit.HEARTS, Denomination.TWO))
            .receiveCard(Card(Suit.SPADES, Denomination.KING))
            .receiveCard(Card(Suit.DIAMONDS, Denomination.KING))

        // when
        val decisionStatus = player.status.ableGetACard()

        // then
        assertThat(decisionStatus).isEqualTo(false)
    }
}
