package blackjack.domain.game

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
}
