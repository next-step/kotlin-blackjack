package blackjack.domain.participant

import blackjack.domain.card.Card
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealerTest {

    @Test
    fun `딜러는 덱에서 여러 장의 카드를 뽑을 수 있다`() {
        // given
        val dealer = Dealer()

        // when
        val cards = dealer.drawCards(2)

        // then
        cards.forEach { card ->
            assertThat(card).isInstanceOf(Card::class.java)
        }
    }

    @Test
    fun `딜러는 덱에서 한 장의 카드를 뽑을 수 있다`() {
        // given
        val dealer = Dealer()

        // when
        val card = dealer.drawOneCard()

        // then
        assertThat(card).isInstanceOf(Card::class.java)
    }

    @Test
    fun `딜러는 가진 패의 합계가 16이하이면 1장의 카드를 추가로 받는다`() {
        TODO()
    }

    @Test
    fun `딜러는 가진 패의 합계가 17이상이면 카드를 추가로 받을 수 없다`() {
        TODO()
    }
}
