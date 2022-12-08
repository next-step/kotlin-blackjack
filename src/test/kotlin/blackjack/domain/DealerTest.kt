package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    internal fun `딜러의 카드 합계가 17점 이하면 카드를 뽑을 수 있다`() {
        // given
        val dealer = Dealer(Cards(Card(Number.QUEEN, Sharp.HEART), Card(Number.SEVEN, Sharp.CLOVER)))

        // when, then
        assertThat(dealer.ableMoreDrawCard()).isFalse
    }

    @Test
    internal fun `딜러의 카드 합계가 17점 이상이면 카드를 뽑을 수 없다`() {
        // given
        val dealer = Dealer(Cards(Card(Number.NINE, Sharp.HEART), Card(Number.SEVEN, Sharp.CLOVER)))

        // when, then
        assertThat(dealer.ableMoreDrawCard()).isTrue
    }
}
