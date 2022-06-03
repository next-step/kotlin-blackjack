package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.game.Dealer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    fun `딜러는 52장의 섞인 카드 덱을 가지고 있다`() {
        // given
        val dealer = Dealer()

        // when, then
        assertThat(dealer.getNumOfRemainCards()).isEqualTo(52)
    }

    @Test
    fun `딜러는 덱에서 카드를 뽑을 수 있다`() {
        // given
        val dealer = Dealer()

        // when
        val card = dealer.drawOneCard()

        // then
        assertThat(card).isInstanceOf(Card::class.java)
        assertThat(dealer.getNumOfRemainCards()).isEqualTo(51)
    }
}
