package blackjack.domain

import blackjack.domain.Number.ACE
import blackjack.domain.Number.NINE
import blackjack.domain.Number.QUEEN
import blackjack.domain.Number.SEVEN
import blackjack.domain.Number.THREE
import blackjack.domain.Number.TWO
import blackjack.domain.Sharp.CLOVER
import blackjack.domain.Sharp.DIAMOND
import blackjack.domain.Sharp.HEART
import blackjack.domain.member.Dealer
import blackjack.domain.member.GamePlayer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    internal fun `딜러의 카드 합계가 17점 이하면 카드를 뽑을 수 있다`() {
        // given
        val dealer = Dealer(Cards(Card(QUEEN, HEART), Card(SEVEN, CLOVER)))

        // when, then
        assertThat(dealer.ableMoreDrawCard()).isFalse
    }

    @Test
    internal fun `딜러의 카드 합계가 17점 이상이면 카드를 뽑을 수 없다`() {
        // given
        val dealer = Dealer(Cards(Card(NINE, HEART), Card(SEVEN, CLOVER)))

        // when, then
        assertThat(dealer.ableMoreDrawCard()).isTrue
    }

    @Test
    internal fun `딜러의 카드 합계가 21 이하이면 fasle 를 반환한다`() {
        // given
        val dealer = Dealer(Cards(Card(NINE, HEART), Card(SEVEN, CLOVER)))

        // when, then
        assertThat(dealer.isOverBlackjackNumber()).isFalse
    }

    @Test
    internal fun `딜러의 카드 합계가 21 초과면 fasle 를 반환한다`() {
        // given
        val dealer = Dealer(Cards(Card(ACE, HEART), Card(QUEEN, CLOVER), Card(TWO, DIAMOND)))

        // when, then
        assertThat(dealer.isOverBlackjackNumber()).isTrue
    }

    @Test
    internal fun `딜러의 점수가 21 초과면 딜러가 패배한다`() {
        // given
        val dealer = Dealer(Cards(Card(QUEEN, HEART), Card(QUEEN, CLOVER), Card(TWO, DIAMOND)))
        val player = GamePlayer("koi", Cards(Card(TWO, HEART), Card(THREE, CLOVER)))

        // when, then
        assertThat(dealer.isWin(player)).isFalse
    }

    @Test
    internal fun `딜러의 점수가 21 이하면 21과 번호가 가까운 사용자가 승리한다`() {
        // given
        val dealer = Dealer(Cards(Card(QUEEN, HEART), Card(QUEEN, CLOVER)))
        val losePlayer = GamePlayer("koi", Cards(Card(TWO, HEART), Card(THREE, CLOVER)))
        val winPlayer = GamePlayer("koi2", Cards(Card(ACE, HEART), Card(QUEEN, CLOVER)))

        // when, then
        assertThat(dealer.isWin(losePlayer)).isTrue
        assertThat(dealer.isWin(winPlayer)).isFalse
    }
}
