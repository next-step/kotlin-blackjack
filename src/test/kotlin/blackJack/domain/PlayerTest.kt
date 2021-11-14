package blackJack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

class PlayerTest {

    @Test
    fun `플레이어는 이름이 지정되어 있고, 카드를 가지고 있다`() {
        // given
        val player = Player.of("김형준")

        // when
        player.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
        player.receiveCard(Card(Suit.SPADES, Denomination.KING))

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
            player.receiveCard(Card(Suit.SPADES, Denomination.KING))
            player.receiveCard(Card(Suit.SPADES, Denomination.KING))
        }.exceptionOrNull()

        // then
        assertThat(actual).hasMessageContaining("중복 된 카드가 있습니다.")
    }

    @Test
    fun `플레이어의 현재 점수는 11점이다`() {
        // given
        val player = Player.of("김형준")
        player.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
        player.receiveCard(Card(Suit.SPADES, Denomination.KING))

        // when
        val currentScore = player.status.getCurrentScore()

        // then
        assertThat(currentScore).isEqualTo(11)
    }

    @Test
    fun `플레이어의 현재 점수는 11점이면 21이하이기 때문에 카드를 더 받을 수 있다 해당 상태는 히트이다`() {
        // given
        val player = Player.of("김형준")
        player.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
        player.receiveCard(Card(Suit.SPADES, Denomination.KING))
        val playerStatus = player.status

        // when
        val decisionStatus = playerStatus.decisionStatus
        val isContinue = playerStatus.ableGetACard()

        // then
        assertAll({
            assertThat(decisionStatus is Hit).isEqualTo(true)
            assertThat(isContinue).isEqualTo(true)
        })
    }

    @Test
    fun `플레이어의 현재 점수는 11점이면 21점이하면 플레이어는 카드를 안받을 수 있다 안받는 다면 상태는 스테이이다`() {
        // given
        val noWantedPlayer = Player.of("김형준")
        noWantedPlayer.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
        noWantedPlayer.receiveCard(Card(Suit.SPADES, Denomination.KING))
        val noWantReceiveCard = noWantedPlayer.status.noWantReceiveCard()

        // when
        val decisionStatus = noWantReceiveCard.decisionStatus
        val isContinue = noWantReceiveCard.ableGetACard()

        // then
        assertAll({
            assertThat(decisionStatus is Stay).isEqualTo(true)
            assertThat(isContinue).isEqualTo(false)
        })
    }

    @Test
    fun `플레이어의 현재 점수가 21점이면 플레이어의 상태는 블랙잭이다`() {
        // given
        val player = Player.of("김형준")
        player.receiveCard(Card(Suit.HEARTS, Denomination.ACE))
        player.receiveCard(Card(Suit.SPADES, Denomination.KING))
        player.receiveCard(Card(Suit.DIAMONDS, Denomination.KING))
        val playerStatus = player.status

        // when
        val decisionStatus = playerStatus.decisionStatus
        val isContinue = playerStatus.ableGetACard()

        // then
        assertAll({
            assertThat(decisionStatus is BlackJack).isEqualTo(true)
            assertThat(isContinue).isEqualTo(false)
        })
    }

    @Test
    fun `플레이어의 현재 점수는 22점이면 21이상이기 때문에 카드를 더 받을 수 있다 해당 상태는 버스트이다`() {
        // given
        val player = Player.of("김형준")
        player.receiveCard(Card(Suit.HEARTS, Denomination.TWO))
        player.receiveCard(Card(Suit.SPADES, Denomination.KING))
        player.receiveCard(Card(Suit.DIAMONDS, Denomination.KING))

        val playerStatus = player.status

        // when
        val decisionStatus = playerStatus.decisionStatus
        val isContinue = playerStatus.ableGetACard()

        // then
        assertAll({
            assertThat(decisionStatus is Bust).isEqualTo(true)
            assertThat(isContinue).isEqualTo(false)
        })
    }
}
