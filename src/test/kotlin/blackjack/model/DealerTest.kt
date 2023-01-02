package blackjack.model

import blackjack.model.Denomination.ACE
import blackjack.model.Denomination.EIGHT
import blackjack.model.Denomination.NINE
import blackjack.model.Denomination.QUEEN
import blackjack.model.Denomination.TEN
import blackjack.model.DualResult.LOSE
import blackjack.model.DualResult.PUSH
import blackjack.model.DualResult.WIN
import blackjack.model.Suit.CLOVER
import blackjack.model.Suit.HEART
import blackjack.model.Suit.SPADE
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class DealerTest {
    @Test
    internal fun `카드를 뽑으면 딜러의 카드목록에 추가된다`() {
        // given
        val dealer = Dealer()

        // when
        dealer.addCard(Card(SPADE, TEN))
        dealer.addCard(Card(SPADE, QUEEN))

        // then
        assertThat(dealer.cards.size).isEqualTo(2)
    }

    @Test
    internal fun `딜러가 카드를 뽑을 수 있는지 판단한다`() {
        // given
        val dealer = Dealer()

        // when
        dealer.addCard(Card(SPADE, EIGHT))
        dealer.addCard(Card(HEART, EIGHT))

        // then
        assertThat(dealer.isPickable()).isTrue
    }

    @Test
    internal fun `카드 합이 16을 넘을 경우 카드를 얻지 못한다`() {
        // given
        val dealer = Dealer()

        // when
        dealer.addCard(Card(SPADE, NINE))
        dealer.addCard(Card(HEART, EIGHT))

        // then
        assertThat(dealer.isPickable()).isFalse
        assertThrows<IllegalStateException> { dealer.addCard(Card(SPADE, ACE)) }
    }

    @Test
    internal fun `다른 플레이어보다 점수가 더 높으면 승리한다`() {
        // given
        val winCards = Cards.of(Card(SPADE, ACE), Card(SPADE, TEN))
        val loseCards = Cards.of(Card(SPADE, TEN), Card(HEART, TEN))

        // when
        val dealer = Dealer(winCards)
        val player = Player("loser", loseCards)

        // then
        assertThat(dealer.wins(player)).isSameAs(WIN)
        assertThat(player.wins(dealer)).isSameAs(LOSE)
    }

    @Test
    internal fun `버스트 되었다면 무조건 패배한다`() {
        // given
        val cards = Cards.of(Card(SPADE, TEN), Card(CLOVER, TEN), Card(HEART, TEN))

        // when
        val dealer = Dealer(cards)
        val player = Player("loser", cards)

        // then
        assertThat(dealer.wins(player)).isSameAs(LOSE)
        assertThat(player.wins(dealer)).isSameAs(LOSE)
    }

    @Test
    internal fun `점수가 같다면 동점으로 판단한다`() {
        // given
        val cards = Cards.of(Card(SPADE, TEN), Card(CLOVER, TEN))

        // when
        val dealer = Dealer(cards)
        val player = Player("loser", cards)

        // then
        assertThat(dealer.wins(player)).isSameAs(PUSH)
        assertThat(player.wins(dealer)).isSameAs(PUSH)
    }
}
