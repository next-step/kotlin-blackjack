package blackjack.model

import blackjack.model.Denomination.ACE
import blackjack.model.Denomination.NINE
import blackjack.model.Denomination.QUEEN
import blackjack.model.Denomination.TEN
import blackjack.model.GameResult.LOSE
import blackjack.model.GameResult.PUSH
import blackjack.model.GameResult.WIN
import blackjack.model.Suit.CLOVER
import blackjack.model.Suit.HEART
import blackjack.model.Suit.SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {
    @Test
    internal fun `플레이어 이름을 입력받는다`() {
        // given
        val name = "jason"

        // when
        val player = Player(name)

        // then
        assertThat(player.name).isEqualTo(name)
    }

    @Test
    internal fun `카드를 뽑으면 플레이어 카드목록에 추가된다`() {
        // given
        val player = Player("jason")

        // when
        player.addCard(Card(SPADE, TEN))
        player.addCard(Card(SPADE, QUEEN))

        // then
        assertThat(player.cards.size).isEqualTo(2)
    }

    @Test
    internal fun `플레이어가 카드를 뽑을 수 있는지 판단한다`() {
        // given
        val player = Player("jason")

        // when
        player.addCard(Card(SPADE, TEN))
        player.addCard(Card(SPADE, QUEEN))

        // then
        assertThat(player.isPickable()).isTrue
    }

    @Test
    internal fun `카드 합이 21을 넘을 경우 카드를 얻지 못한다`() {
        // given
        val player = Player("jason")

        // when
        player.addCard(Card(SPADE, NINE))
        player.addCard(Card(HEART, NINE))
        player.addCard(Card(CLOVER, NINE))

        // then
        assertThat(player.isPickable()).isFalse
        assertThrows<IllegalStateException> { player.addCard(Card(SPADE, ACE)) }
    }

    @Test
    internal fun `다른 플레이어보다 점수가 더 높으면 승리한다`() {
        // given
        val winCards = Cards.of(Card(SPADE, ACE), Card(SPADE, TEN))
        val loseCards = Cards.of(Card(SPADE, TEN), Card(HEART, TEN))

        // when
        val loser = Player("loser", loseCards)
        val winner = Player("winner", winCards)

        // then
        assertThat(loser.wins(winner)).isSameAs(LOSE)
        assertThat(winner.wins(loser)).isSameAs(WIN)
    }

    @Test
    internal fun `버스트 되었다면 무조건 패배한다`() {
        // given
        val cards = Cards.of(Card(SPADE, TEN), Card(CLOVER, TEN), Card(HEART, TEN))

        // when
        val bustPlayer = Player("loser", cards)
        val bustPlayer2 = Player("loser", cards)

        // then
        assertThat(bustPlayer.wins(bustPlayer2)).isSameAs(LOSE)
        assertThat(bustPlayer2.wins(bustPlayer)).isSameAs(LOSE)
    }

    @Test
    internal fun `점수가 같다면 동점으로 판단한다`() {
        // given
        val cards = Cards.of(Card(SPADE, TEN), Card(CLOVER, TEN))

        // when
        val player1 = Player("loser", cards)
        val player2 = Player("loser", cards)

        // then
        assertThat(player1.wins(player2)).isSameAs(PUSH)
    }
}
